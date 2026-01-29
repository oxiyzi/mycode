package fitness;
import java.time.LocalDate;
import java.time.LocalTime;

import static fitness.Zone.POOL;

public class OneDayMembership extends Membership {

    public OneDayMembership(Person owner) {
        super(owner, LocalDate.now());
    }

    @Override
    public boolean isZoneAllowed(Zone zone) {
        return zone == Zone.GYM || zone == Zone.POOL;
    }

    @Override
    public boolean isTimeAllowed(LocalTime time){
        return time.getHour() >= 8 && time.getHour() < 22;
    }
}
