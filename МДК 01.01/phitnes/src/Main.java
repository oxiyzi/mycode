package fitness;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        FitnessClub club = new FitnessClub();

        Person p = new Person("Иван", "Иванов", 1998);
        Membership membership =
                new FullMembership(p, LocalDate.now().plusMonths(1));

        club.register(membership, Zone.GYM);
        club.register(membership, Zone.POOL);

        club.closeClub();
    }
}

