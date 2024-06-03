
package MMM.demo.Daos;

import MMM.demo.Entities.TransactionsMembership;
import MMM.demo.Repositories.TransactionsMembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.time.OffsetDateTime;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TransactionsMembershipDaoImpl implements TransactionsMembershipRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TransactionsMembership> findAll() {
        String sql = "SELECT * FROM transactions_memberships";
        return jdbcTemplate.query(sql, new TransactionsMembershipRowMapper());
    }

    private static class TransactionsMembershipRowMapper implements RowMapper<TransactionsMembership> {
        @Override
        public TransactionsMembership mapRow(ResultSet rs, int rowNum) throws SQLException {
            TransactionsMembership result = new TransactionsMembership();
            result.setId_transaction(rs.getInt("id_transaction"));
            result.setId_membership(rs.getInt("id_membership"));
            result.setId_member(rs.getInt("id_member"));
            result.setOrder_time(rs.getObject("order_time", OffsetDateTime.class));
            return result;
        }
    }
}
