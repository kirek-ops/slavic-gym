
package MMM.demo.Daos;

import MMM.demo.Entities.Categories;
import MMM.demo.Repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoriesDaoImpl implements CategoriesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Categories> findAll() {
        String sql = "SELECT * FROM categories";
        return jdbcTemplate.query(sql, new CategoriesRowMapper());
    }

    private static class CategoriesRowMapper implements RowMapper<Categories> {
        @Override
        public Categories mapRow(ResultSet rs, int rowNum) throws SQLException {
            Categories result = new Categories();
            result.setIdCategory(rs.getInt("id_category"));
            result.setCategoryName(rs.getString("category_name"));
            result.setParentCategoryId(rs.getInt("parent_category_id"));
            return result;
        }
    }
}
