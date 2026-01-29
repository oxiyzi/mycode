package fitness;
import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Membership {
    protected Person owner;
    protected LocalDate startDate;
    protected LocalDate endDate;

    public Membership(Person owner, LocalDate endDate) {
        this.owner = owner;
        this.startDate = LocalDate.now();
        this.endDate = endDate;
    }


    public Person getOwner() {
        return owner;
    }

    public boolean isValid() {
        LocalDate today = LocalDate.now();
        return !today.isBefore(startDate) && !today.isAfter(endDate);
    }

    public abstract boolean isZoneAllowed(Zone zone);
    public abstract boolean isTimeAllowed(LocalTime time);
}
