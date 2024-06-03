
package MMM.demo.Daos;

import MMM.demo.Entities.Bookings;
import MMM.demo.Repositories.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookingsDaoImpl implements BookingsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Bookings> findAll() {
        String sql = "SELECT * FROM bookings";
        return jdbcTemplate.query(sql, new BookingsRowMapper());
    }

    private static class BookingsRowMapper implements RowMapper<Bookings> {
        @Override
        public Bookings mapRow(ResultSet rs, int rowNum) throws SQLException {
            Bookings result = new Bookings();
            result.setIdBooking(rs.getInt("id_booking"));
            result.setIdMember(rs.getInt("id_member"));
            result.setIdClass(rs.getInt("id_class"));
            return result;
        }
    }
}
