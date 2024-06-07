
package MMM.demo.Daos;

import MMM.demo.Entities.ExerciseLogsTime;
import MMM.demo.Entities.ExerciseLogTime;
import MMM.demo.Repositories.ExerciseLogsTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import java.time.Duration;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ExerciseLogsTimeDaoImpl implements ExerciseLogsTimeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List <ExerciseLogsTime> findAll() {
        String sql = "SELECT * FROM exercise_logs_time";
        return jdbcTemplate.query(sql, new ExerciseLogsTimeRowMapper());
    }

    public int insertLog(ExerciseLogsTime curLog) {
        String sql = "INSERT INTO exercise_logs_time (id_log, id_member, id_exercise, log_date, minutes_done) " +
                    "VALUES (?, ?, ?, ?, ?)";
        Object[] params = {
            curLog.getId_log(),
            curLog.getId_member(),
            curLog.getId_exercise(),
            curLog.getLog_date(),
            curLog.getMinutes_done()
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

    public List <ExerciseLogTime> getLogsByIdWithName(Integer id) {
        String sql = "SELECT elt.*, te.exercise_name FROM " +
                    "exercise_logs_time elt JOIN time_exercises te ON te.id_exercise = elt.id_exercise " +
                    "WHERE elt.id_member = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new ExerciseLogTimeRowMapper());
    }

    private static class ExerciseLogTimeRowMapper implements RowMapper<ExerciseLogTime> {
        @Override
        public ExerciseLogTime mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExerciseLogTime result = new ExerciseLogTime();
            result.setId_log(rs.getInt("id_log"));
            result.setId_member(rs.getInt("id_member"));
            result.setId_exercise(rs.getInt("id_exercise"));
            result.setLog_date(rs.getDate("log_date").toLocalDate());
            result.setMinutes_done(rs.getInt("minutes_done"));
            result.setExercise_name(rs.getString("exercise_name"));
            return result;
        }
    }

    private static class ExerciseLogsTimeRowMapper implements RowMapper<ExerciseLogsTime> {
        @Override
        public ExerciseLogsTime mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExerciseLogsTime result = new ExerciseLogsTime();
            result.setId_log(rs.getInt("id_log"));
            result.setId_member(rs.getInt("id_member"));
            result.setId_exercise(rs.getInt("id_exercise"));
            result.setLog_date(rs.getDate("log_date").toLocalDate());
            result.setMinutes_done(rs.getInt("minutes_done"));
            return result;
        }
    }
}
