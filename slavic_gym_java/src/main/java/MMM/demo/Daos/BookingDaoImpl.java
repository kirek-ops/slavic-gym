
package MMM.demo.Daos;

import MMM.demo.Entities.Booking;
import MMM.demo.Repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.sql.Types;

import java.time.LocalDate;
import java.time.LocalTime;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class BookingDaoImpl implements BookingRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Booking> findAll() {
        String sql = "SELECT * FROM bookings";
        return jdbcTemplate.query(sql, new BookingRowMapper());
    }

    public int insertBooking(Booking booking) throws Exception {
        String sql = "INSERT INTO bookings (id_booking, id_member, id_class) " +
                     "VALUES (?, ?, ?)";

        Object[] params = {
            booking.getId_booking(),
            booking.getId_member(),
            booking.getId_class()
        };
        int[] types = {
            Types.INTEGER,
            Types.INTEGER,
            Types.INTEGER
        };
        return jdbcTemplate.update(sql, params, types);
    }

    private static class BookingRowMapper implements RowMapper<Booking> {
        @Override
        public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
            Booking result = new Booking();
            result.setId_booking(rs.getInt("id_booking"));
            result.setId_member(rs.getInt("id_member"));
            result.setId_class(rs.getInt("id_class"));
            return result;
        }
    }
}
