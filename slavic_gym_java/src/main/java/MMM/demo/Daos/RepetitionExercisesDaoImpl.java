
package MMM.demo.Daos;

import MMM.demo.Entities.RepetitionExercises;
import MMM.demo.Repositories.RepetitionExercisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepetitionExercisesDaoImpl implements RepetitionExercisesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RepetitionExercises> findAll() {
        String sql = "SELECT * FROM repetition_exercises";
        return jdbcTemplate.query(sql, new RepetitionExercisesRowMapper());
    }

    private static class RepetitionExercisesRowMapper implements RowMapper<RepetitionExercises> {
        @Override
        public RepetitionExercises mapRow(ResultSet rs, int rowNum) throws SQLException {
            RepetitionExercises result = new RepetitionExercises();
            result.setIdExercise(rs.getInt("id_exercise"));
            result.setExerciseName(rs.getString("exercise_name"));
            return result;
        }
    }
}
