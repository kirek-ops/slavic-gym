
package MMM.demo.Daos;

import MMM.demo.Entities.ExerciseLogsTime;
import MMM.demo.Repositories.ExerciseLogsTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ExerciseLogsTimeDaoImpl implements ExerciseLogsTimeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ExerciseLogsTime> findAll() {
        String sql = "SELECT * FROM exercise_logs_time";
        return jdbcTemplate.query(sql, new ExerciseLogsTimeRowMapper());
    }

    private static class ExerciseLogsTimeRowMapper implements RowMapper<ExerciseLogsTime> {
        @Override
        public ExerciseLogsTime mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExerciseLogsTime result = new ExerciseLogsTime();
            result.setIdLog(rs.getInt("id_log"));
            result.setIdMember(rs.getInt("id_member"));
            result.setIdGoal(rs.getInt("id_goal"));
            result.setLogDate(rs.getObject("log_date", ZonedDateTime.class));
            result.setTimeDone(Duration.ofMillis(rs.getLong("time_done")));
            return result;
        }
    }
}
