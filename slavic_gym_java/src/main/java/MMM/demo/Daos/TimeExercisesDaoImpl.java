
package MMM.demo.Daos;

import MMM.demo.Entities.TimeExercises;
import MMM.demo.Repositories.TimeExercisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TimeExercisesDaoImpl implements TimeExercisesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TimeExercises> findAll() {
        String sql = "SELECT * FROM time_exercises";
        return jdbcTemplate.query(sql, new TimeExercisesRowMapper());
    }

    private static class TimeExercisesRowMapper implements RowMapper<TimeExercises> {
        @Override
        public TimeExercises mapRow(ResultSet rs, int rowNum) throws SQLException {
            TimeExercises result = new TimeExercises();
            result.setIdExercise(rs.getInt("id_exercise"));
            result.setExerciseName(rs.getString("exercise_name"));
            return result;
        }
    }
}
