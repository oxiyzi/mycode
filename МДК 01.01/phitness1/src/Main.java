import java.time.LocalDate;
import java.time.LocalTime;

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

// ---------- PERSON ----------
class Person {
    private String name;
    private String surname;
    private int birthYear;

    public Person(String name, String surname, int birthYear) {
        this.name = name;
        this.surname = surname;
        this.birthYear = birthYear;
    }

    public String getFullName() {
        return name + " " + surname;
    }
}

// ---------- ZONE ----------
enum Zone {
    GYM,
    POOL,
    GROUP
}

// ---------- MEMBERSHIP ----------
abstract class Membership {

    protected Person owner;
    protected LocalDate startDate;
    protected LocalDate endDate;

    public Membership(Person owner, LocalDate endDate) {
        this.owner = owner;
        this.startDate = LocalDate.now();
        this.endDate = endDate;
    }

    public boolean isValid() {
        LocalDate today = LocalDate.now();
        return !today.isBefore(startDate) && !today.isAfter(endDate);
    }

    public abstract boolean isZoneAllowed(Zone zone);
    public abstract boolean isTimeAllowed(LocalTime time);
}

// ---------- ONE DAY ----------
class OneDayMembership extends Membership {

    public OneDayMembership(Person owner) {
        super(owner, LocalDate.now());
    }

    @Override
    public boolean isZoneAllowed(Zone zone) {
        return zone == Zone.GYM || zone == Zone.POOL;
    }

    @Override
    public boolean isTimeAllowed(LocalTime time) {
        return time.getHour() >= 8 && time.getHour() < 22;
    }
}

// ---------- DAY ----------
class DayMembership extends Membership {

    public DayMembership(Person owner, LocalDate endDate) {
        super(owner, endDate);
    }

    @Override
    public boolean isZoneAllowed(Zone zone) {
        return zone == Zone.GYM || zone == Zone.GROUP;
    }

    @Override
    public boolean isTimeAllowed(LocalTime time) {
        return time.getHour() >= 8 && time.getHour() < 16;
    }
}

// ---------- FULL ----------
class FullMembership extends Membership {

    public FullMembership(Person owner, LocalDate endDate) {
        super(owner, endDate);
    }

    @Override
    public boolean isZoneAllowed(Zone zone) {
        return true;
    }

    @Override
    public boolean isTimeAllowed(LocalTime time) {
        return time.getHour() >= 8 && time.getHour() < 22;
    }
}

// ---------- FITNESS CLUB ----------
class FitnessClub {

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
                        membership.owner.getFullName() +
                                " вошёл в " + zone
                );
                return;
            }
        }

        System.out.println("Нет свободных мест");
    }

    private Membership[] getArray(Zone zone) {
        if (zone == Zone.GYM) return gym;
        if (zone == Zone.POOL) return pool;
        return group;
    }

    public void closeClub() {
        gym = new Membership[20];
        pool = new Membership[20];
        group = new Membership[20];
        System.out.println("Фитнес-клуб закрыт");
    }
}