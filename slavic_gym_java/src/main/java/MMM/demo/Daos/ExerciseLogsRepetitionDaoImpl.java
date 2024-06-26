
package MMM.demo.Daos;

import MMM.demo.Entities.ExerciseLogsRepetition;
import MMM.demo.Entities.ExerciseLogRep;
import MMM.demo.Repositories.ExerciseLogsRepetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import java.time.LocalDate;
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

    public int insertLog(ExerciseLogsRepetition curLog) {
        String sql = "INSERT INTO exercise_logs_repetitions (id_log, id_member, id_exercise, log_date, reps_done) " +
                    "VALUES (?, ?, ?, ?, ?)";
        Object[] params = {
            curLog.getId_log(),
            curLog.getId_member(),
            curLog.getId_exercise(),
            curLog.getLog_date(),
            curLog.getReps_done()
        };
        int[] types = {
            Types.INTEGER,
            Types.INTEGER,
            Types.INTEGER,
            Types.DATE,
            Types.INTEGER
        };
        return jdbcTemplate.update(sql, params, types);
    }

    public List <ExerciseLogRep> getLogsByIdWithName(Integer id) {
        String sql = "SELECT elr.*, re.exercise_name FROM " +
                    "exercise_logs_repetitions elr JOIN repetition_exercises re ON re.id_exercise = elr.id_exercise " +
                    "WHERE elr.id_member = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new ExerciseLogRepRowMapper());
    }

    private static class ExerciseLogRepRowMapper implements RowMapper<ExerciseLogRep> {
        @Override
        public ExerciseLogRep mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExerciseLogRep result = new ExerciseLogRep();
            result.setId_log(rs.getInt("id_log"));
            result.setId_member(rs.getInt("id_member"));
            result.setId_exercise(rs.getInt("id_exercise"));
            result.setLog_date(rs.getDate("log_date").toLocalDate());
            result.setReps_done(rs.getInt("reps_done"));
            result.setExercise_name(rs.getString("exercise_name"));
            return result;
        }
    }

    private static class ExerciseLogsRepetitionRowMapper implements RowMapper<ExerciseLogsRepetition> {
        @Override
        public ExerciseLogsRepetition mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExerciseLogsRepetition result = new ExerciseLogsRepetition();
            result.setId_log(rs.getInt("id_log"));
            result.setId_member(rs.getInt("id_member"));
            result.setId_exercise(rs.getInt("id_exercise"));
            result.setLog_date(rs.getDate("log_date").toLocalDate());
            result.setReps_done(rs.getInt("reps_done"));
            return result;
        }
    }
}
