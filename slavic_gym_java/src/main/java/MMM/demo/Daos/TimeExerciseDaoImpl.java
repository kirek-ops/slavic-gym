
package MMM.demo.Daos;

import MMM.demo.Entities.TimeExercise;
import MMM.demo.Repositories.TimeExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TimeExerciseDaoImpl implements TimeExerciseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TimeExercise> findAll() {
        String sql = "SELECT * FROM time_exercises";
        return jdbcTemplate.query(sql, new TimeExerciseRowMapper());
    }

    private static class TimeExerciseRowMapper implements RowMapper<TimeExercise> {
        @Override
        public TimeExercise mapRow(ResultSet rs, int rowNum) throws SQLException {
            TimeExercise result = new TimeExercise();
            result.setId_exercise(rs.getInt("id_exercise"));
            result.setExercise_name(rs.getString("exercise_name"));
            return result;
        }
    }
}
