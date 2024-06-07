package MMM.demo.Daos;

import MMM.demo.Entities.Images;
import MMM.demo.Repositories.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ImagesDaoImpl implements ImagesRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Images> findAll() {
        String sql = "SELECT * FROM image_item";
        return jdbcTemplate.query(sql, new ImagesRowMapper());
    }

    @Override
    public Integer findImageIdByItemId(Integer id_item) {
        String sql = "SELECT id_image FROM image_item WHERE id_item = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id_item}, Integer.class);
        } catch (Exception e) {
            // Handle the case where the id_item does not exist
            return null;
        }
    }

    private static class ImagesRowMapper implements RowMapper<Images> {
        @Override
        public Images mapRow(ResultSet rs, int rowNum) throws SQLException {
            Images image = new Images();
            image.setId_item(rs.getInt("id_item"));
            image.setId_image(rs.getInt("id_image"));
            return image;
        }
    }
}
