
package MMM.demo.Daos;

import MMM.demo.Entities.EmployeePosition;
import MMM.demo.Repositories.EmployeePositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class EmployeePositionDaoImpl implements EmployeePositionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EmployeePosition> findAll() {
        String sql = "SELECT * FROM employee_positions";
        return jdbcTemplate.query(sql, new EmployeePositionRowMapper());
    }

    private static class EmployeePositionRowMapper implements RowMapper<EmployeePosition> {
        @Override
        public EmployeePosition mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployeePosition result = new EmployeePosition();
            result.setId_employee(rs.getInt("id_employee"));
            result.setId_position(rs.getInt("id_position"));
            result.setStart_date(rs.getDate("start_date").toLocalDate());
            result.setEnd_date(rs.getDate("end_date").toLocalDate());
            return result;
        }
    }
}
