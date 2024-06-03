
package MMM.demo.Repositories;

import MMM.demo.Entities.Booking;
import java.util.List;

public interface BookingRepository {
    List<Booking> findAll();
}
