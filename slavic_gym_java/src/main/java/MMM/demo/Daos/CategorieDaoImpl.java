
package MMM.demo.Daos;

import MMM.demo.Entities.Categorie;
import MMM.demo.Repositories.CategorieRepository;
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
public class CategorieDaoImpl implements CategorieRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Categorie> findAll() {
        String sql = "SELECT * FROM categories";
        return jdbcTemplate.query(sql, new CategorieRowMapper());
    }

    public Categorie findById (Integer id) {
        String sql = "SELECT * FROM products_categories " +
                    "JOIN categories ON products_categories.id_category = categories.id_category " +
                    "WHERE products_categories.id_item = ?";
        return jdbcTemplate.queryForObject(sql, new CategorieRowMapper(), id);
    }

    private static class CategorieRowMapper implements RowMapper<Categorie> {
        @Override
        public Categorie mapRow(ResultSet rs, int rowNum) throws SQLException {
            Categorie result = new Categorie();
            result.setId_category(rs.getInt("id_category"));
            result.setCategory_name(rs.getString("category_name"));
            return result;
        }
    }
}
