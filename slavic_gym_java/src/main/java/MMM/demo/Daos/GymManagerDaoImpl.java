
package MMM.demo.Daos;

import MMM.demo.Entities.GymManager;
import MMM.demo.Repositories.GymManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class GymManagerDaoImpl implements GymManagerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<GymManager> findAll() {
        String sql = "SELECT * FROM gym_managers";
        return jdbcTemplate.query(sql, new GymManagerRowMapper());
    }

    private static class GymManagerRowMapper implements RowMapper<GymManager> {
        @Override
        public GymManager mapRow(ResultSet rs, int rowNum) throws SQLException {
            GymManager result = new GymManager();
            result.setId_gym(rs.getInt("id_gym"));
            result.setId_manager(rs.getInt("id_manager"));
            result.setStart_date(rs.getDate("start_date").toLocalDate());
            result.setEnd_date(rs.getDate("end_date").toLocalDate());
            return result;
        }
    }
}
