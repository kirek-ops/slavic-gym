
package MMM.demo.Daos;

import MMM.demo.Entities.Classe;
import MMM.demo.Repositories.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.sql.Types;

import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.ResultSet;
import java.sql.SQLException;

import MMM.demo.Entities.ClassForPerson;

@Slf4j
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

    public List <ClassForPerson> getClassesForPerson(
        Integer id_gym,
        LocalDate date_from,
        LocalDate date_to
    ) {
        String sql = "SELECT c.id_class, c.class_name, c.schedule, c.time_from, c.time_till, g.name, gm.first_name, gm.last_name, c.capacity " +
                "FROM classes c " +
                "JOIN gyms g ON g.id_gym = c.id_gym " +
                "JOIN gym_members gm ON gm.id_member = c.id_instructor " +
                "WHERE (CAST(? AS INTEGER) IS NULL OR c.id_gym = ?) " +
                "AND (CAST(? AS DATE) IS NULL OR c.schedule >= ?) " +
                "AND (CAST(? AS DATE) IS NULL OR c.schedule <= ?) " +
                "ORDER BY c.schedule, c.time_from LIMIT 12";
            
        if (date_from == null) {
            date_from = LocalDate.now().plusDays(1);
        } else {
            date_from = date_from.isBefore(LocalDate.now().plusDays(1)) ? LocalDate.now().plusDays(1) : date_from;
        }

        if (date_to != null && date_from.isAfter(date_to)) {
            log.warn("date_from {} is after date_to {}. Returning an empty list.", date_from, date_to);
            return null;
        }
        
        log.info("Obtaining classes in: {} {} {}", id_gym, date_from, date_to);
        log.info("Nulls: {} {} {}", (id_gym == null), (date_from == null), (date_to == null));
        log.info(sql);

        List <ClassForPerson> result = jdbcTemplate.query(sql, new Object[]{id_gym, id_gym, date_from, date_from, date_to, date_to}, new ClassForPersonRowMapper());
        return result;
    }

    private static class ClassForPersonRowMapper implements RowMapper <ClassForPerson> {
        @Override
        public ClassForPerson mapRow(ResultSet rs, int rowNum) throws SQLException {
            ClassForPerson result = new ClassForPerson();
            result.setId_class(rs.getInt("id_class"));
            result.setClass_name(rs.getString("class_name"));
            result.setSchedule(rs.getDate("schedule").toLocalDate());
            result.setTime_from(rs.getTime("time_from").toLocalTime());
            result.setTime_till(rs.getTime("time_till").toLocalTime());
            result.setGym_name(rs.getString("name"));
            result.setTrainer(rs.getString("first_name") + " " + rs.getString("last_name"));
            result.setCapacity(rs.getInt("capacity"));
            result.setFilled(0);
            return result;
        }
    }
}
