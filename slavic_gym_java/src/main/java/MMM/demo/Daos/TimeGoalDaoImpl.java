
package MMM.demo.Daos;

import MMM.demo.Entities.TimeGoal;
import MMM.demo.Repositories.TimeGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.time.Duration;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TimeGoalDaoImpl implements TimeGoalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TimeGoal> findAll() {
        String sql = "SELECT * FROM time_goals";
        return jdbcTemplate.query(sql, new TimeGoalRowMapper());
    }

    private static class TimeGoalRowMapper implements RowMapper<TimeGoal> {
        @Override
        public TimeGoal mapRow(ResultSet rs, int rowNum) throws SQLException {
            TimeGoal result = new TimeGoal();
            result.setId_goal(rs.getInt("id_goal"));
            result.setId_member(rs.getInt("id_member"));
            result.setId_exercise(rs.getInt("id_exercise"));
            result.setTarget_time(Duration.ofMillis(rs.getLong("target_time")));
            return result;
        }
    }
}
