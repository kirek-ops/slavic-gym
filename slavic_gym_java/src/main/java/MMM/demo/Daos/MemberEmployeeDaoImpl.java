
package MMM.demo.Daos;

import MMM.demo.Entities.MemberEmployee;
import MMM.demo.Repositories.MemberEmployeeRepository;
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
public class MemberEmployeeDaoImpl implements MemberEmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MemberEmployee> findAll() {
        String sql = "SELECT * FROM member_employees";
        return jdbcTemplate.query(sql, new MemberEmployeeRowMapper());
    }

    private static class MemberEmployeeRowMapper implements RowMapper<MemberEmployee> {
        @Override
        public MemberEmployee mapRow(ResultSet rs, int rowNum) throws SQLException {
            MemberEmployee result = new MemberEmployee();
            result.setId_member(rs.getInt("id_member"));
            result.setId_employee(rs.getInt("id_employee"));
            return result;
        }
    }
}
