
package MMM.demo.Daos;

import MMM.demo.Entities.Positions;
import MMM.demo.Repositories.PositionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PositionsDaoImpl implements PositionsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Positions> findAll() {
        String sql = "SELECT * FROM positions";
        return jdbcTemplate.query(sql, new PositionsRowMapper());
    }

    private static class PositionsRowMapper implements RowMapper<Positions> {
        @Override
        public Positions mapRow(ResultSet rs, int rowNum) throws SQLException {
            Positions result = new Positions();
            result.setIdPosition(rs.getInt("id_position"));
            result.setPositionName(rs.getString("position_name"));
            return result;
        }
    }
}
