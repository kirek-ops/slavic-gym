
package MMM.demo.Daos;

import MMM.demo.Entities.GymManagers;
import MMM.demo.Repositories.GymManagersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GymManagersDaoImpl implements GymManagersRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<GymManagers> findAll() {
        String sql = "SELECT * FROM gym_managers";
        return jdbcTemplate.query(sql, new GymManagersRowMapper());
    }

    private static class GymManagersRowMapper implements RowMapper<GymManagers> {
        @Override
        public GymManagers mapRow(ResultSet rs, int rowNum) throws SQLException {
            GymManagers result = new GymManagers();
            result.setIdGym(rs.getInt("id_gym"));
            result.setIdManager(rs.getInt("id_manager"));
            result.setStartDate(rs.getDate("start_date").toLocalDate());
            result.setEndDate(rs.getDate("end_date").toLocalDate());
            return result;
        }
    }
}
