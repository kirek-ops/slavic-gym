package MMM.demo.Resources;

import MMM.demo.Daos.ClasseDaoImpl;
import MMM.demo.Daos.BookingDaoImpl;
import MMM.demo.Entities.Classe;
import MMM.demo.Entities.Booking;
import MMM.demo.Entities.ClassForPerson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import MMM.demo.Utils.LocationFetcher;
import MMM.demo.Utils.UuidGenerator;
import MMM.demo.Utils.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.SQLException;

@Slf4j
@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingsResource {
    private final ClasseDaoImpl classeDaoImpl;
    private final BookingDaoImpl bookingDaoImpl;

    @PostMapping("/get-classes")
    public ResponseEntity < List <ClassForPerson> > getClasses(@RequestBody Map <String, Object> requestBody) {
      log.info("Sending: {} {} {}", requestBody.get("id_gym"), requestBody.get("time_from"), requestBody.get("time_from"));

      List <ClassForPerson> result = classeDaoImpl.getClassesForPerson(
        (Integer)requestBody.get("id_gym"),
        requestBody.get("time_from") == null ? null : LocalDate.parse((String)requestBody.get("time_from")),
        requestBody.get("time_till") == null ? null : LocalDate.parse((String)requestBody.get("time_till"))
      );

      if (result == null) {
        log.info("Incorrect date");
        return ResponseEntity.badRequest().body(null);
      }
      return ResponseEntity.ok(result);
    }

    @PostMapping("/book/{id}")
    public ResponseEntity < String > bookClass(@PathVariable Integer id, @RequestBody Map <String, Object> requestBody) {
      Booking booking = new Booking();
      try {
        booking.setId_booking(new UuidGenerator("id_bookings").generateUniqueID());
      } catch (Exception e) {
        log.info("Error while generating id for booking. Aborting");
        return ResponseEntity.badRequest().body("Error while generating id");
      }

      booking.setId_class((Integer)requestBody.get("id_class"));
      booking.setId_member(id);

      try {
        int result = bookingDaoImpl.insertBooking(booking);
        return ResponseEntity.ok("Succesfully booked the class");
      } catch (Exception e) {
        log.info(e.getMessage());
        log.info(ErrorCutter.cut(e.getMessage()));
        return ResponseEntity.badRequest().body(ErrorCutter.cut(e.getMessage()));
      }
    }

    @GetMapping("/get-booked-classes/{id}")
    public ResponseEntity < List <ClassForPerson> > getBooked(@PathVariable Integer id) {
      return ResponseEntity.ok(null);
      // return bookingDaoImpl.getBookedClassesFromToday(id);
    }
}
