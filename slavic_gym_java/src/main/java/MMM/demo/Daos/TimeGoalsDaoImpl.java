
package MMM.demo.Daos;

import MMM.demo.Entities.TimeGoals;
import MMM.demo.Repositories.TimeGoalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TimeGoalsDaoImpl implements TimeGoalsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TimeGoals> findAll() {
        String sql = "SELECT * FROM time_goals";
        return jdbcTemplate.query(sql, new TimeGoalsRowMapper());
    }

    private static class TimeGoalsRowMapper implements RowMapper<TimeGoals> {
        @Override
        public TimeGoals mapRow(ResultSet rs, int rowNum) throws SQLException {
            TimeGoals result = new TimeGoals();
            result.setIdGoal(rs.getInt("id_goal"));
            result.setIdMember(rs.getInt("id_member"));
            result.setIdExercise(rs.getInt("id_exercise"));
            result.setTargetTime(Duration.ofMillis(rs.getLong("target_time")));
            return result;
        }
    }
}
