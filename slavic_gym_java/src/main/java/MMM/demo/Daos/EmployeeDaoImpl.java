
package MMM.demo.Daos;

import MMM.demo.Entities.Employee;
import MMM.demo.Entities.EmployeePositionName;
import MMM.demo.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class EmployeeDaoImpl implements EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    public Boolean isEmployeeByID(Integer id) {
        String sql = "SELECT COUNT(*) FROM employees WHERE id_employee = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Integer[]{id}, Integer.class);
        return count != null && count > 0;
    }

    public Map <Integer, String> positions_gyms_ById(Integer id) {
        String sql = 
            "SELECT e.id_employee, ep.id_gym, p.position_name " +
            "FROM employees e " +
            "JOIN employee_positions ep ON ep.id_employee = e.id_employee " +
            "JOIN positions p ON p.id_position = ep.id_position " + 
            "WHERE e.id_employee = ? AND ep.end_date IS NOT NULL";

        List <EmployeePositionName> resultingList = jdbcTemplate.query(sql, new Integer[]{id}, new EmployeePositionNameRowMapper());

        return resultingList.stream()
                .filter(employee -> employee.getId_employee().equals(id))
                .collect(Collectors.toMap(EmployeePositionName::getId_gym, EmployeePositionName::getPosition_name));
    }

    private static class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee result = new Employee();
            result.setId_employee(rs.getInt("id_employee"));
            result.setFirst_name(rs.getString("first_name"));
            result.setLast_name(rs.getString("last_name"));
            return result;
        }
    }

    private static class EmployeePositionNameRowMapper implements RowMapper<EmployeePositionName> {
        @Override
        public EmployeePositionName mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployeePositionName result = new EmployeePositionName();
            result.setId_employee(rs.getInt("id_employee"));
            result.setId_gym(rs.getInt("id_gym"));
            result.setPosition_name(rs.getString("position_name"));
            return result;
        }
    }
}
