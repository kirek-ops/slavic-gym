
package MMM.demo.Daos;

import MMM.demo.Entities.TransactionsInventory;
import MMM.demo.Repositories.TransactionsInventoryRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

import java.time.OffsetDateTime;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
@Slf4j
public class TransactionsInventoryDaoImpl implements TransactionsInventoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TransactionsInventory> findAll() {
        String sql = "SELECT * FROM transactions_inventory";
        return jdbcTemplate.query(sql, new TransactionsInventoryRowMapper());
    }



    public int insertTransaction(TransactionsInventory transaction) {
        log.info("Inserting transaction: " + transaction.toString());
        String sql = "INSERT INTO transactions_inventory (id_transaction, id_item, id_member, order_time, quantity) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, transaction.getId_transaction(), transaction.getId_item(), transaction.getId_member(),
                Timestamp.valueOf(transaction.getOrder_time().toLocalDateTime()), transaction.getQuantity());
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
