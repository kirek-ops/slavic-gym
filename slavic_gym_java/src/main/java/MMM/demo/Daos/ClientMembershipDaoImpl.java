
package MMM.demo.Daos;

import MMM.demo.Entities.ClientMembership;
import MMM.demo.Repositories.ClientMembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientMembershipDaoImpl implements ClientMembershipRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ClientMembership> findAll() {
        String sql = "SELECT * FROM client_membership";
        return jdbcTemplate.query(sql, new ClientMembershipRowMapper());
    }

    private static class ClientMembershipRowMapper implements RowMapper<ClientMembership> {
        @Override
        public ClientMembership mapRow(ResultSet rs, int rowNum) throws SQLException {
            ClientMembership result = new ClientMembership();
            result.setIdMember(rs.getInt("id_member"));
            result.setIdMembership(rs.getInt("id_membership"));
            result.setStartDate(rs.getDate("start_date").toLocalDate());
            return result;
        }
    }
}
