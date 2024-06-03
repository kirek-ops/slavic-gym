
package MMM.demo.Daos;

import MMM.demo.Entities.EmployeePositions;
import MMM.demo.Repositories.EmployeePositionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeePositionsDaoImpl implements EmployeePositionsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EmployeePositions> findAll() {
        String sql = "SELECT * FROM employee_positions";
        return jdbcTemplate.query(sql, new EmployeePositionsRowMapper());
    }

    private static class EmployeePositionsRowMapper implements RowMapper<EmployeePositions> {
        @Override
        public EmployeePositions mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployeePositions result = new EmployeePositions();
            result.setIdEmployee(rs.getInt("id_employee"));
            result.setIdPosition(rs.getInt("id_position"));
            result.setStartDate(rs.getDate("start_date").toLocalDate());
            result.setEndDate(rs.getDate("end_date").toLocalDate());
            return result;
        }
    }
}
