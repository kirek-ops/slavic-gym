
package MMM.demo.Daos;

import MMM.demo.Entities.Position;
import MMM.demo.Repositories.PositionRepository;
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
public class PositionDaoImpl implements PositionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Position> findAll() {
        String sql = "SELECT * FROM positions";
        return jdbcTemplate.query(sql, new PositionRowMapper());
    }

    private static class PositionRowMapper implements RowMapper<Position> {
        @Override
        public Position mapRow(ResultSet rs, int rowNum) throws SQLException {
            Position result = new Position();
            result.setId_position(rs.getInt("id_position"));
            result.setPosition_name(rs.getString("position_name"));
            return result;
        }
    }
}
