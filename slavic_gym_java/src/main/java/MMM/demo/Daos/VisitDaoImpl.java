
package MMM.demo.Daos;

import MMM.demo.Entities.Visit;
import MMM.demo.Repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.time.OffsetDateTime;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class VisitDaoImpl implements VisitRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Visit> findAll() {
        String sql = "SELECT * FROM visits";
        return jdbcTemplate.query(sql, new VisitRowMapper());
    }

    @Override
    public List<Visit> findByMemberId(Integer id_member) {
        String sql = "SELECT * FROM visits WHERE id_member = ?";
        return jdbcTemplate.query(sql, new VisitRowMapper(), id_member);
    }

    private static class VisitRowMapper implements RowMapper<Visit> {
        @Override
        public Visit mapRow(ResultSet rs, int rowNum) throws SQLException {
            Visit result = new Visit();
            result.setId_visit(rs.getInt("id_visit"));
            result.setId_member(rs.getInt("id_member"));
            result.setVisit_time(rs.getObject("visit_time", OffsetDateTime.class));
            return result;
        }
    }
}
