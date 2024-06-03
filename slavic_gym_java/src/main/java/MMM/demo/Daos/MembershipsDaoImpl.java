
package MMM.demo.Daos;

import MMM.demo.Entities.Memberships;
import MMM.demo.Repositories.MembershipsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MembershipsDaoImpl implements MembershipsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Memberships> findAll() {
        String sql = "SELECT * FROM memberships";
        return jdbcTemplate.query(sql, new MembershipsRowMapper());
    }

    private static class MembershipsRowMapper implements RowMapper<Memberships> {
        @Override
        public Memberships mapRow(ResultSet rs, int rowNum) throws SQLException {
            Memberships result = new Memberships();
            result.setIdMembership(rs.getInt("id_membership"));
            result.setIsActive(rs.getBoolean("is_active"));
            result.setPrice(rs.getBigDecimal("price"));
            result.setDuration(rs.getInt("duration"));
            return result;
        }
    }
}
