
package MMM.demo.Daos;

import MMM.demo.Entities.Membership;
import MMM.demo.Repositories.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MembershipDaoImpl implements MembershipRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Membership> findAll() {
        String sql = "SELECT * FROM memberships";
        return jdbcTemplate.query(sql, new MembershipRowMapper());
    }

    private static class MembershipRowMapper implements RowMapper<Membership> {
        @Override
        public Membership mapRow(ResultSet rs, int rowNum) throws SQLException {
            Membership result = new Membership();
            result.setId_membership(rs.getInt("id_membership"));
            result.setIs_active(rs.getBoolean("is_active"));
            result.setPrice(rs.getBigDecimal("price"));
            result.setDuration(rs.getInt("duration"));
            return result;
        }
    }
}
