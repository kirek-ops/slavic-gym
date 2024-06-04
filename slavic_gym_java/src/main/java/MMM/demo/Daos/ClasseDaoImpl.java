
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
import java.sql.Types;

import java.time.LocalDate;
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

    public List <Classe> getAllFromToday(Integer id_instructor) {
        String sql =
            "SELECT * FROM classes " +
            "WHERE id_instructor = ? AND schedule >= CURRENT_DATE";
        
        List <Classe> result = jdbcTemplate.query(sql, new Object[]{id_instructor}, new ClasseRowMapper());
        return result;
    }

    public List <Classe> getAllFromTodayInGym(Integer id_instructor, Integer id_gym) {
        String sql =
            "SELECT * FROM classes " +
            "WHERE id_instructor = ? AND id_gym = ?";
        
        List <Classe> result = jdbcTemplate.query(sql, new Object[]{id_instructor, id_gym}, new ClasseRowMapper());
        return result;
    }

    public int insertClass(Classe classe) throws Exception {
        String sql = "INSERT INTO classes (id_class, class_name, schedule, time_from, time_till, id_gym, capacity, id_instructor) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Object[] params = {
            classe.getId_class(),
            classe.getClass_name(),
            classe.getSchedule(),
            classe.getTime_from(),
            classe.getTime_till(),
            classe.getId_gym(),
            classe.getCapacity(),
            classe.getId_instructor()
        };
        int[] types = {
            Types.INTEGER,
            Types.VARCHAR,
            Types.DATE,
            Types.TIME,
            Types.TIME,
            Types.INTEGER,
            Types.INTEGER,
            Types.INTEGER
        };
        return jdbcTemplate.update(sql, params, types);
    }

    private static class ClasseRowMapper implements RowMapper<Classe> {
        @Override
        public Classe mapRow(ResultSet rs, int rowNum) throws SQLException {
            Classe result = new Classe();
            result.setId_class(rs.getInt("id_class"));
            result.setClass_name(rs.getString("class_name"));
            result.setSchedule(rs.getDate("schedule").toLocalDate());
            result.setTime_from(rs.getTime("time_from").toLocalTime());
            result.setTime_till(rs.getTime("time_till").toLocalTime());
            result.setId_gym(rs.getInt("id_gym"));
            result.setCapacity(rs.getInt("capacity"));
            result.setId_instructor(rs.getInt("id_instructor"));
            return result;
        }
    }
}
