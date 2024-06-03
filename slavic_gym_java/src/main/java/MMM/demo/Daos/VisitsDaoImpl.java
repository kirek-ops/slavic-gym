
package MMM.demo.Daos;

import MMM.demo.Entities.Visits;
import MMM.demo.Repositories.VisitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VisitsDaoImpl implements VisitsRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Visits> findAll() {
        String sql = "SELECT * FROM visits";
        return jdbcTemplate.query(sql, new VisitsRowMapper());
    }

    private static class VisitsRowMapper implements RowMapper<Visits> {
        @Override
        public Visits mapRow(ResultSet rs, int rowNum) throws SQLException {
            Visits result = new Visits();
            result.setIdVisit(rs.getInt("id_visit"));
            result.setIdMember(rs.getInt("id_member"));
            result.setVisitTime(rs.getObject("visit_time", ZonedDateTime.class));
            return result;
        }
    }
}
