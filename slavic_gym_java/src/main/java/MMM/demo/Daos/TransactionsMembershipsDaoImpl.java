
package MMM.demo.Daos;

import MMM.demo.Entities.TransactionsMemberships;
import MMM.demo.Repositories.TransactionsMembershipsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TransactionsMembershipsDaoImpl implements TransactionsMembershipsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TransactionsMemberships> findAll() {
        String sql = "SELECT * FROM transactions_memberships";
        return jdbcTemplate.query(sql, new TransactionsMembershipsRowMapper());
    }

    private static class TransactionsMembershipsRowMapper implements RowMapper<TransactionsMemberships> {
        @Override
        public TransactionsMemberships mapRow(ResultSet rs, int rowNum) throws SQLException {
            TransactionsMemberships result = new TransactionsMemberships();
            result.setIdTransaction(rs.getInt("id_transaction"));
            result.setIdMembership(rs.getInt("id_membership"));
            result.setIdMember(rs.getInt("id_member"));
            result.setOrderTime(rs.getObject("order_time", ZonedDateTime.class));
            return result;
        }
    }
}
