
package MMM.demo.Daos;

import MMM.demo.Entities.Employees;
import MMM.demo.Repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeesDaoImpl implements EmployeesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Employees> findAll() {
        String sql = "SELECT * FROM employees";
        return jdbcTemplate.query(sql, new EmployeesRowMapper());
    }

    private static class EmployeesRowMapper implements RowMapper<Employees> {
        @Override
        public Employees mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employees result = new Employees();
            result.setIdEmployee(rs.getInt("id_employee"));
            result.setFirstName(rs.getString("first_name"));
            result.setLastName(rs.getString("last_name"));
            return result;
        }
    }
}
