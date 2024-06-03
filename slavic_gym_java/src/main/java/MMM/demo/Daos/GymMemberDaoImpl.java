
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

    // Creating new gym member
    public void createGymMember(GymMember gymMember) {
        String sql = "INSERT INTO gym_members (id_member, first_name, last_name, email, phone_number, join_date, passwd) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, gymMember.getId_member(), gymMember.getFirst_name(), gymMember.getLast_name(), gymMember.getEmail(), gymMember.getPhone_number(), gymMember.getJoin_date(), gymMember.getPasswd());
    }

    // Find by email gym member
    public GymMember findByEmail(String email) {
        String sql = "SELECT * FROM gym_members WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new GymMemberRowMapper(), email);
    }

    public Boolean existsMember(String email, String phone) {
        // System.out.println("A!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        String sql = "SELECT COUNT(*) FROM gym_members WHERE email LIKE ? OR phone_number LIKE ?";
        Integer count = jdbcTemplate.queryForObject(sql, new String[]{email, phone}, Integer.class);
        // System.out.println("B!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return count != null && count > 0;
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
