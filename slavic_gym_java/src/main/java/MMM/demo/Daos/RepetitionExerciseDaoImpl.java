
package MMM.demo.Daos;

import MMM.demo.Entities.RepetitionExercise;
import MMM.demo.Repositories.RepetitionExerciseRepository;
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
public class RepetitionExerciseDaoImpl implements RepetitionExerciseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RepetitionExercise> findAll() {
        String sql = "SELECT * FROM repetition_exercises";
        return jdbcTemplate.query(sql, new RepetitionExerciseRowMapper());
    }

    private static class RepetitionExerciseRowMapper implements RowMapper<RepetitionExercise> {
        @Override
        public RepetitionExercise mapRow(ResultSet rs, int rowNum) throws SQLException {
            RepetitionExercise result = new RepetitionExercise();
            result.setId_exercise(rs.getInt("id_exercise"));
            result.setExercise_name(rs.getString("exercise_name"));
            return result;
        }
    }
}
