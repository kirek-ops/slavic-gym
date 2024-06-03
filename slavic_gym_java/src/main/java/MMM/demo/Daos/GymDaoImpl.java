
package MMM.demo.Daos;

import MMM.demo.Entities.Gym;
import MMM.demo.Repositories.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class GymDaoImpl implements GymRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Gym> findAll() {
        String sql = "SELECT * FROM gyms";
        return jdbcTemplate.query(sql, new GymRowMapper());
    }

    private static class GymRowMapper implements RowMapper<Gym> {
        @Override
        public Gym mapRow(ResultSet rs, int rowNum) throws SQLException {
            Gym result = new Gym();
            result.setId_gym(rs.getInt("id_gym"));
            result.setName(rs.getString("name"));
            result.setStreet(rs.getString("street"));
            result.setCity(rs.getString("city"));
            result.setPostal_code(rs.getString("postal_code"));
            result.setContact_number(rs.getString("contact_number"));
            return result;
        }
    }
}
