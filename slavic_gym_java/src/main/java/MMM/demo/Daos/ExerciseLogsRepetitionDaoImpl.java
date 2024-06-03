
package MMM.demo.Daos;

import MMM.demo.Entities.ExerciseLogsRepetition;
import MMM.demo.Repositories.ExerciseLogsRepetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.time.OffsetDateTime;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ExerciseLogsRepetitionDaoImpl implements ExerciseLogsRepetitionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ExerciseLogsRepetition> findAll() {
        String sql = "SELECT * FROM exercise_logs_repetitions";
        return jdbcTemplate.query(sql, new ExerciseLogsRepetitionRowMapper());
    }

    private static class ExerciseLogsRepetitionRowMapper implements RowMapper<ExerciseLogsRepetition> {
        @Override
        public ExerciseLogsRepetition mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExerciseLogsRepetition result = new ExerciseLogsRepetition();
            result.setId_log(rs.getInt("id_log"));
            result.setId_member(rs.getInt("id_member"));
            result.setId_goal(rs.getInt("id_goal"));
            result.setLog_date(rs.getObject("log_date", OffsetDateTime.class));
            result.setReps_done(rs.getInt("reps_done"));
            return result;
        }
    }
}
