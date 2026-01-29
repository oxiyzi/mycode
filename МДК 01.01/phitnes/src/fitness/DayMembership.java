package fitness;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DayMembership extends Membership {

    public DayMembership(Person owner, LocalDate endDate) {
        super(owner, endDate);
    }

    @Override
    public boolean isZoneAllowed(Zone zone) {
        return zone == Zone.GYM || zone == Zone.POOL;
    }

    @Override
    public boolean isTimeAllowed(LocalTime Time) {
        LocalDateTime time = null;
        return time.getHour() >= 8 && time.getHour() < 16;
    }
}
