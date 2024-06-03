
package MMM.demo.Daos;

import MMM.demo.Entities.Classes;
import MMM.demo.Repositories.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClassesDaoImpl implements ClassesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Classes> findAll() {
        String sql = "SELECT * FROM classes";
        return jdbcTemplate.query(sql, new ClassesRowMapper());
    }

    private static class ClassesRowMapper implements RowMapper<Classes> {
        @Override
        public Classes mapRow(ResultSet rs, int rowNum) throws SQLException {
            Classes result = new Classes();
            result.setIdClass(rs.getInt("id_class"));
            result.setClassName(rs.getString("class_name"));
            result.setSchedule(rs.getTimestamp("schedule").toLocalDateTime());
            result.setTimeFrom(rs.getTime("time_from").toLocalTime());
            result.setTimeTill(rs.getTime("time_till").toLocalTime());
            result.setIdGym(rs.getInt("id_gym"));
            result.setCapacity(rs.getInt("capacity"));
            result.setIdInstructor(rs.getInt("id_instructor"));
            return result;
        }
    }
}
