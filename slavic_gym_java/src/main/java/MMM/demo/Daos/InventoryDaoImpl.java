package MMM.demo.Daos;

import MMM.demo.Entities.Inventory;
import MMM.demo.Repositories.InventoryRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.id.IncrementGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class InventoryDaoImpl implements InventoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Inventory> findAll() {
        String sql = "SELECT * FROM inventory";
        return jdbcTemplate.query(sql, new InventoryRowMapper());
    }

    public List <Inventory> findAllByGymId (Integer id) {
        String sql = "SELECT * FROM inventory WHERE id_gym = ?";
        return jdbcTemplate.query(sql, new InventoryRowMapper(), id);
    }

    public Inventory findByItemId (Integer item, Integer gym) {
        String sql = "SELECT * FROM inventory WHERE id_item = ? AND id_gym = ?";
        return jdbcTemplate.queryForObject(sql, new InventoryRowMapper(), item, gym);
    }

    public void insertProduct(Inventory inventory, Integer category) {
        String sql = "INSERT INTO inventory (id_item, item_name, quantity, id_gym, price) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, inventory.getId_item(), inventory.getItem_name(), inventory.getQuantity(), inventory.getId_gym(), inventory.getPrice());
        sql = "INSERT INTO products_categories (id_item, id_category) VALUES (?, ?)";
        jdbcTemplate.update(sql, inventory.getId_item(), category);
    }

    private static class InventoryRowMapper implements RowMapper<Inventory> {
        @Override
        public Inventory mapRow(ResultSet rs, int rowNum) throws SQLException {
            Inventory result = new Inventory();
            result.setId_item(rs.getInt("id_item"));
            result.setItem_name(rs.getString("item_name"));
            result.setQuantity(rs.getInt("quantity"));
            result.setId_gym(rs.getInt("id_gym"));
            result.setPrice(rs.getFloat("price"));
            return result;
        }
    }
}
