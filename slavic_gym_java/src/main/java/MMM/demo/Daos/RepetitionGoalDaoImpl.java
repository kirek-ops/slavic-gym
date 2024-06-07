
package MMM.demo.Daos;

import MMM.demo.Entities.RepetitionGoal;
import MMM.demo.Repositories.RepetitionGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;


import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class RepetitionGoalDaoImpl implements RepetitionGoalRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RepetitionGoal> findAll() {
        String sql = "SELECT * FROM repetition_goals";
        return jdbcTemplate.query(sql, new RepetitionGoalRowMapper());
    }

    public int insertGoal(RepetitionGoal goal) {
        String sql = "INSERT INTO repetition_goals (id_goal, id_member, id_exercise, reps_target) " +
                    "VALUES (?, ?, ?, ?)";
        Object[] params = {
            goal.getId_goal(),
            goal.getId_member(),
            goal.getId_exercise(),
            goal.getReps_target()
        };
        int[] types = {
            Types.INTEGER,
            Types.INTEGER,
            Types.INTEGER,
            Types.INTEGER
        };
        return jdbcTemplate.update(sql, params, types);
    }

    private static class RepetitionGoalRowMapper implements RowMapper<RepetitionGoal> {
        @Override
        public RepetitionGoal mapRow(ResultSet rs, int rowNum) throws SQLException {
            RepetitionGoal result = new RepetitionGoal();
            result.setId_goal(rs.getInt("id_goal"));
            result.setId_member(rs.getInt("id_member"));
            result.setId_exercise(rs.getInt("id_exercise"));
            result.setReps_target(rs.getInt("reps_target"));
            return result;
        }
    }
}
