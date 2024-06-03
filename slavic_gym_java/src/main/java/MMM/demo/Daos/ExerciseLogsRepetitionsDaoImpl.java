
package MMM.demo.Daos;

import MMM.demo.Entities.ExerciseLogsRepetitions;
import MMM.demo.Repositories.ExerciseLogsRepetitionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ExerciseLogsRepetitionsDaoImpl implements ExerciseLogsRepetitionsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ExerciseLogsRepetitions> findAll() {
        String sql = "SELECT * FROM exercise_logs_repetitions";
        return jdbcTemplate.query(sql, new ExerciseLogsRepetitionsRowMapper());
    }

    private static class ExerciseLogsRepetitionsRowMapper implements RowMapper<ExerciseLogsRepetitions> {
        @Override
        public ExerciseLogsRepetitions mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExerciseLogsRepetitions result = new ExerciseLogsRepetitions();
            result.setIdLog(rs.getInt("id_log"));
            result.setIdMember(rs.getInt("id_member"));
            result.setIdGoal(rs.getInt("id_goal"));
            result.setLogDate(rs.getObject("log_date", ZonedDateTime.class));
            result.setRepsDone(rs.getInt("reps_done"));
            return result;
        }
    }
}
