
package MMM.demo.Daos;

import MMM.demo.Entities.RepetitionGoals;
import MMM.demo.Repositories.RepetitionGoalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RepetitionGoalsDaoImpl implements RepetitionGoalsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RepetitionGoals> findAll() {
        String sql = "SELECT * FROM repetition_goals";
        return jdbcTemplate.query(sql, new RepetitionGoalsRowMapper());
    }

    private static class RepetitionGoalsRowMapper implements RowMapper<RepetitionGoals> {
        @Override
        public RepetitionGoals mapRow(ResultSet rs, int rowNum) throws SQLException {
            RepetitionGoals result = new RepetitionGoals();
            result.setIdGoal(rs.getInt("id_goal"));
            result.setIdMember(rs.getInt("id_member"));
            result.setIdExercise(rs.getInt("id_exercise"));
            result.setTargetReps(rs.getInt("target_reps"));
            return result;
        }
    }
}
