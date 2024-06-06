
package MMM.demo.Daos;

import MMM.demo.Entities.TransactionsInventory;
import MMM.demo.Repositories.TransactionsInventoryRepository;
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
import java.util.Map;

@Repository
public class TransactionsInventoryDaoImpl implements TransactionsInventoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TransactionsInventory> findAll() {
        String sql = "SELECT * FROM transactions_inventory";
        return jdbcTemplate.query(sql, new TransactionsInventoryRowMapper());
    }



    private static class TransactionsInventoryRowMapper implements RowMapper<TransactionsInventory> {
        @Override
        public TransactionsInventory mapRow(ResultSet rs, int rowNum) throws SQLException {
            TransactionsInventory result = new TransactionsInventory();
            result.setId_transaction(rs.getInt("id_transaction"));
            result.setId_item(rs.getInt("id_item"));
            result.setId_member(rs.getInt("id_member"));
            result.setOrder_time(rs.getObject("order_time", OffsetDateTime.class));
            result.setQuantity(rs.getInt("quantity"));
            return result;
        }
    }
}
