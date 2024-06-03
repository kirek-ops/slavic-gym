
package MMM.demo.Daos;

import MMM.demo.Entities.ProductsCategories;
import MMM.demo.Repositories.ProductsCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductsCategoriesDaoImpl implements ProductsCategoriesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ProductsCategories> findAll() {
        String sql = "SELECT * FROM products_categories";
        return jdbcTemplate.query(sql, new ProductsCategoriesRowMapper());
    }

    private static class ProductsCategoriesRowMapper implements RowMapper<ProductsCategories> {
        @Override
        public ProductsCategories mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProductsCategories result = new ProductsCategories();
            result.setIdItem(rs.getInt("id_item"));
            result.setIdCategory(rs.getInt("id_category"));
            return result;
        }
    }
}
