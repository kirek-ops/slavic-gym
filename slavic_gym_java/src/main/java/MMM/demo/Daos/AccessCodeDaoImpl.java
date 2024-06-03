
package MMM.demo.Daos;

import MMM.demo.Entities.AccessCode;
import MMM.demo.Repositories.AccessCodeRepository;
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
public class AccessCodeDaoImpl implements AccessCodeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<AccessCode> findAll() {
        String sql = "SELECT * FROM access_codes";
        return jdbcTemplate.query(sql, new AccessCodeRowMapper());
    }

    private static class AccessCodeRowMapper implements RowMapper<AccessCode> {
        @Override
        public AccessCode mapRow(ResultSet rs, int rowNum) throws SQLException {
            AccessCode result = new AccessCode();
            result.setCode_id(rs.getString("code_id"));
            result.setGenerated_at(rs.getObject("generated_at", OffsetDateTime.class));
            result.setId_member(rs.getInt("id_member"));
            result.setFirst_used_at(rs.getObject("first_used_at", OffsetDateTime.class));
            return result;
        }
    }
}
