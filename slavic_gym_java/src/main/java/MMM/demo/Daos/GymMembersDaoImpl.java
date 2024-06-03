
package MMM.demo.Daos;

import MMM.demo.Entities.GymMembers;
import MMM.demo.Repositories.GymMembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GymMembersDaoImpl implements GymMembersRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<GymMembers> findAll() {
        String sql = "SELECT * FROM gym_members";
        return jdbcTemplate.query(sql, new GymMembersRowMapper());
    }

    private static class GymMembersRowMapper implements RowMapper<GymMembers> {
        @Override
        public GymMembers mapRow(ResultSet rs, int rowNum) throws SQLException {
            GymMembers result = new GymMembers();
            result.setIdMember(rs.getInt("id_member"));
            result.setFirstName(rs.getString("first_name"));
            result.setLastName(rs.getString("last_name"));
            result.setEmail(rs.getString("email"));
            result.setPhoneNumber(rs.getString("phone_number"));
            result.setJoinDate(rs.getDate("join_date").toLocalDate());
            result.setPasswd(rs.getString("passwd"));
            return result;
        }
    }
}
