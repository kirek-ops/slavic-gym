
package MMM.demo.Daos;

import MMM.demo.Entities.TimeGoal;
import MMM.demo.Repositories.TimeGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import java.time.LocalDate;
import MMM.demo.Entities.*;

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

    public int insertGoal(TimeGoal goal) {
        String sql = "INSERT INTO time_goals (id_goal, id_member, id_exercise, minutes_target) " +
                    "VALUES (?, ?, ?, ?)";
        Object[] params = {
            goal.getId_goal(),
            goal.getId_member(),
            goal.getId_exercise(),
            goal.getMinutes_target()
        };
        int[] types = {
            Types.INTEGER,
            Types.INTEGER,
            Types.INTEGER,
            Types.INTEGER
        };
        return jdbcTemplate.update(sql, params, types);
    }

    public List <TimeGoalWithCompleted> getByIdWithCompletionAndName(Integer id) {
        String sql = "SELECT tg.*, te.exercise_name, get_first_date_when_goal_reached_time(tg.id_goal) AS date_reach " +
                    "FROM time_goals tg " +
                    "JOIN time_exercises te ON te.id_exercise = tg.id_exercise " +
                    "WHERE tg.id_member = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new TimeGoalWithCompletedRowMapper());
    }

    private static class TimeGoalWithCompletedRowMapper implements RowMapper<TimeGoalWithCompleted> {
        @Override
        public TimeGoalWithCompleted mapRow(ResultSet rs, int rowNum) throws SQLException {
            TimeGoalWithCompleted result = new TimeGoalWithCompleted();
            result.setId_goal(rs.getInt("id_goal"));
            result.setId_member(rs.getInt("id_member"));
            result.setId_exercise(rs.getInt("id_exercise"));
            result.setMinutes_target(rs.getInt("minutes_target"));
            result.setName_exercise(rs.getString("exercise_name"));
            var date_reach = rs.getDate("date_reach");
            result.setCompletion_date(date_reach == null ? null : date_reach.toLocalDate());
            return result;
        }
    }

    private static class TimeGoalRowMapper implements RowMapper<TimeGoal> {
        @Override
        public TimeGoal mapRow(ResultSet rs, int rowNum) throws SQLException {
            TimeGoal result = new TimeGoal();
            result.setId_goal(rs.getInt("id_goal"));
            result.setId_member(rs.getInt("id_member"));
            result.setId_exercise(rs.getInt("id_exercise"));
            result.setMinutes_target(rs.getInt("minutes_target"));
            return result;
        }
    }
}
