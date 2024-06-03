
package MMM.demo.Daos;

import MMM.demo.Entities.MemberEmployees;
import MMM.demo.Repositories.MemberEmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MemberEmployeesDaoImpl implements MemberEmployeesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MemberEmployees> findAll() {
        String sql = "SELECT * FROM member_employees";
        return jdbcTemplate.query(sql, new MemberEmployeesRowMapper());
    }

    private static class MemberEmployeesRowMapper implements RowMapper<MemberEmployees> {
        @Override
        public MemberEmployees mapRow(ResultSet rs, int rowNum) throws SQLException {
            MemberEmployees result = new MemberEmployees();
            result.setIdMember(rs.getInt("id_member"));
            result.setIdEmployee(rs.getInt("id_employee"));
            return result;
        }
    }
}
