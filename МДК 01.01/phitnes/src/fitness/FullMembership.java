package fitness;

import java.time.LocalDate;
import java.time.LocalTime;

public class FullMembership extends Membership {

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