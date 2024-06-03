
package MMM.demo.Daos;

import MMM.demo.Entities.GymMember;
import MMM.demo.Repositories.GymMemberRepository;
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
public class GymMemberDaoImpl implements GymMemberRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<GymMember> findAll() {
        String sql = "SELECT * FROM gym_members";
        return jdbcTemplate.query(sql, new GymMemberRowMapper());
    }

    private static class GymMemberRowMapper implements RowMapper<GymMember> {
        @Override
        public GymMember mapRow(ResultSet rs, int rowNum) throws SQLException {
            GymMember result = new GymMember();
            result.setId_member(rs.getInt("id_member"));
            result.setFirst_name(rs.getString("first_name"));
            result.setLast_name(rs.getString("last_name"));
            result.setEmail(rs.getString("email"));
            result.setPhone_number(rs.getString("phone_number"));
            result.setJoin_date(rs.getDate("join_date").toLocalDate());
            result.setPasswd(rs.getString("passwd"));
            return result;
        }
    }
}
