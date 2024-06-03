
package MMM.demo.Daos;

import MMM.demo.Entities.ProductsCategorie;
import MMM.demo.Repositories.ProductsCategorieRepository;
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
public class ProductsCategorieDaoImpl implements ProductsCategorieRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ProductsCategorie> findAll() {
        String sql = "SELECT * FROM products_categories";
        return jdbcTemplate.query(sql, new ProductsCategorieRowMapper());
    }

    private static class ProductsCategorieRowMapper implements RowMapper<ProductsCategorie> {
        @Override
        public ProductsCategorie mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProductsCategorie result = new ProductsCategorie();
            result.setId_item(rs.getInt("id_item"));
            result.setId_category(rs.getInt("id_category"));
            return result;
        }
    }
}
