
package MMM.demo.Daos;

import MMM.demo.Entities.AccessCodes;
import MMM.demo.Repositories.AccessCodesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AccessCodesDaoImpl implements AccessCodesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AccessCodes> findAll() {
        String sql = "SELECT * FROM access_codes";
        return jdbcTemplate.query(sql, new AccessCodesRowMapper());
    }

    private static class AccessCodesRowMapper implements RowMapper<AccessCodes> {
        @Override
        public AccessCodes mapRow(ResultSet rs, int rowNum) throws SQLException {
            AccessCodes result = new AccessCodes();
            result.setCodeId(rs.getString("code_id"));
            result.setGeneratedAt(rs.getObject("generated_at", ZonedDateTime.class));
            result.setIdMember(rs.getInt("id_member"));
            result.setFirstUsedAt(rs.getObject("first_used_at", ZonedDateTime.class));
            return result;
        }
    }
}
