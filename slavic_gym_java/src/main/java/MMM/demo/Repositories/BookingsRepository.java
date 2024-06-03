
package Repositories;

import Entities.Bookings;
import java.util.List;

public interface BookingsRepository {
    List<Bookings> findAll();
    Bookings findById(int id_member, int id_class);
    int addBookings(Bookings bookings);
    int updateBookings(Bookings bookings);
    int deleteBookings(int id_member, int id_class);
}
