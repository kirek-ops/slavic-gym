package MMM.demo.Daos;

import MMM.demo.Entities.MembershipWithStartDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MembershipWithStartDateDaoImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<MembershipWithStartDate> findByMemberId (Integer id) {
        String sql = "SELECT m.*, cm.start_date FROM memberships m " +
                    "JOIN client_membership cm ON m.id_membership = cm.id_membership " +
                    "WHERE cm.id_member = ?";
        return jdbcTemplate.query(sql, new MembershipWithStartDateRowMapper(), id);
    }

    private static class MembershipWithStartDateRowMapper implements RowMapper<MembershipWithStartDate> {
        @Override
        public MembershipWithStartDate mapRow(ResultSet rs, int rowNum) throws SQLException {
            MembershipWithStartDate result = new MembershipWithStartDate();
            result.setName(rs.getString("name"));
            result.setId_membership(rs.getInt("id_membership"));
            result.setIs_active(rs.getBoolean("is_active"));
            result.setPrice(rs.getBigDecimal("price"));
            result.setDuration(rs.getInt("duration"));
            result.setStart_date(rs.getDate("start_date").toLocalDate());
            return result;
        }
    }
}
