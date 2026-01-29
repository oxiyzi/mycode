public class Training {
    private int id;
    private int trainerId;
    private int clientId;
    private String trainingTime;
    public Training(int id, int trainerId, int clientId, String trainingTime) {
        this.id = id;
        this.trainerId = trainerId;
        this.clientId = clientId;
        this.trainingTime = trainingTime;
    }
    public int getId() { return id; }
    public int getTrainerId() { return trainerId; }
    public int getClientId() { return clientId; }
    public String getTrainingTime() { return trainingTime; }
}
