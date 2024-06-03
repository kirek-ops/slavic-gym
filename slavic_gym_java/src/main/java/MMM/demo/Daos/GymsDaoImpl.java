
package MMM.demo.Daos;

import MMM.demo.Entities.Gyms;
import MMM.demo.Repositories.GymsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GymsDaoImpl implements GymsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Gyms> findAll() {
        String sql = "SELECT * FROM gyms";
        return jdbcTemplate.query(sql, new GymsRowMapper());
    }

    private static class GymsRowMapper implements RowMapper<Gyms> {
        @Override
        public Gyms mapRow(ResultSet rs, int rowNum) throws SQLException {
            Gyms result = new Gyms();
            result.setIdGym(rs.getInt("id_gym"));
            result.setName(rs.getString("name"));
            result.setStreet(rs.getString("street"));
            result.setCity(rs.getString("city"));
            result.setPostalCode(rs.getString("postal_code"));
            result.setContactNumber(rs.getString("contact_number"));
            return result;
        }
    }
}
