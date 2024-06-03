
package MMM.demo.Daos;

import MMM.demo.Entities.Classe;
import MMM.demo.Repositories.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ClasseDaoImpl implements ClasseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Classe> findAll() {
        String sql = "SELECT * FROM classes";
        return jdbcTemplate.query(sql, new ClasseRowMapper());
    }

    private static class ClasseRowMapper implements RowMapper<Classe> {
        @Override
        public Classe mapRow(ResultSet rs, int rowNum) throws SQLException {
            Classe result = new Classe();
            result.setId_class(rs.getInt("id_class"));
            result.setClass_name(rs.getString("class_name"));
            result.setSchedule(rs.getTimestamp("schedule").toLocalDateTime());
            result.setTime_from(rs.getTime("time_from").toLocalTime());
            result.setTime_till(rs.getTime("time_till").toLocalTime());
            result.setId_gym(rs.getInt("id_gym"));
            result.setCapacity(rs.getInt("capacity"));
            result.setId_instructor(rs.getInt("id_instructor"));
            return result;
        }
    }
}
