
package MMM.demo.Repositories;

import MMM.demo.Entities.Bookings;
import java.util.List;

public interface BookingsRepository {
    List<Bookings> findAll();
}
