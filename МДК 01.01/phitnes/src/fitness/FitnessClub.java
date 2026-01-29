package fitness;

import java.time.LocalTime;

public class FitnessClub {

    private Membership[] gym = new Membership[20];
    private Membership[] pool = new Membership[20];
    private Membership[] group = new Membership[20];

    public void register(Membership membership, Zone zone) {
        if (!membership.isValid()) {
            System.out.println("Абонемент недействителен");
            return;
        }

        if (!membership.isZoneAllowed(zone)) {
            System.out.println("Доступ запрещён");
            return;
        }

        if (!membership.isTimeAllowed(LocalTime.now())) {
            System.out.println("Посещение запрещено по времени");
            return;
        }
        Membership[] arr = getArray(zone);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                arr[i] = membership;
                System.out.println(
                        membership.getOwner().getFullName()
                                + " вошёл в " + zone
                );
                return;
            }
        }

        System.out.println("Нет свободных мест");
        }

    private Membership[] getArray(Zone zone) {
        if (zone == Zone.GYM) return gym;
        if (zone == Zone.Pool) return pool;
        return group;
    }

    public void closeClub() {
        gym = new Membership[20];
        pool = new Membership[20];
        group = new Membership[20];
        System.out.println("Фитнес-клуб закрыт");
    }
}
