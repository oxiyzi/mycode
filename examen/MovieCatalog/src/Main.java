import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    TableView<Movie> table = new TableView<>();
    MovieDAO dao = new MovieDAO();

    TextField title = new TextField();
    TextField genre = new TextField();
    TextField year = new TextField();
    TextField rating = new TextField();

    ListView<String> reviewList = new ListView<>();

    TextField author = new TextField();
    TextField reviewText = new TextField();
    TextField reviewRating = new TextField();

    Movie selected = null;

    @Override
    public void start(Stage stage) {

        // 🎬 таблица
        TableColumn<Movie, String> c1 = new TableColumn<>("Название");
        c1.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().title));

        TableColumn<Movie, String> c2 = new TableColumn<>("Жанр");
        c2.setCellValueFactory(d -> new javafx.beans.property.SimpleStringProperty(d.getValue().genre));

        TableColumn<Movie, Number> c3 = new TableColumn<>("Год");
        c3.setCellValueFactory(d -> new javafx.beans.property.SimpleIntegerProperty(d.getValue().year));

        TableColumn<Movie, Number> c4 = new TableColumn<>("Рейтинг");
        c4.setCellValueFactory(d -> new javafx.beans.property.SimpleDoubleProperty(d.getValue().rating));

        table.getColumns().addAll(c1, c2, c3, c4);

        table.setOnMouseClicked(e -> {
            selected = table.getSelectionModel().getSelectedItem();

            if (selected != null) {
                loadReviews(selected.id);
            }
        });

        // 💬 поля
        author.setPromptText("Автор");
        reviewText.setPromptText("Отзыв");
        reviewRating.setPromptText("Рейтинг");

        // ➕ добавить отзыв
        Button addReview = new Button("Добавить отзыв");

        addReview.setOnAction(e -> {

            if (selected == null) return;

            dao.addReview(
                    selected.id,
                    author.getText(),
                    reviewText.getText(),
                    Integer.parseInt(reviewRating.getText())
            );

            dao.updateMovieRating(selected.id);

            loadReviews(selected.id);
            loadMovies();
        });

        // ❌ удалить отзыв
        Button deleteReview = new Button("Удалить отзыв");

        deleteReview.setOnAction(e -> {

            if (selected == null) return;

            int index = reviewList.getSelectionModel().getSelectedIndex();

            if (index < 0) return;

            List<Review> reviews = dao.getReviewsByMovieId(selected.id);

            Review r = reviews.get(index);

            dao.deleteReview(r.id);
            dao.updateMovieRating(selected.id);

            loadReviews(selected.id);
            loadMovies();
        });

        VBox reviewBox = new VBox(5,
                new Label("Отзывы"),
                reviewList,
                author,
                reviewText,
                reviewRating,
                addReview,
                deleteReview
        );

        HBox root = new HBox(10, table, reviewBox);

        loadMovies();

        stage.setScene(new Scene(root, 1000, 600));
        stage.setTitle("Каталог фильмов");
        stage.show();
    }

    void loadMovies() {
        List<Movie> movies = dao.getAll();

        ObservableList<Movie> data =
                FXCollections.observableArrayList(movies);

        table.setItems(data);
    }

    void loadReviews(int movieId) {

        List<Review> reviews = dao.getReviewsByMovieId(movieId);

        ObservableList<String> data = FXCollections.observableArrayList();

        for (Review r : reviews) {
            data.add(r.author + ": " + r.text + " (" + r.rating + ")");
        }

        reviewList.setItems(data);
    }

    public static void main(String[] args) {
        launch();
    }
}