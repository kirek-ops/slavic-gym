package MMM.demo.Daos;

import MMM.demo.Entities.Membership;
import MMM.demo.Entities.TransactionsMembership;
import MMM.demo.Repositories.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class MembershipDaoImpl implements MembershipRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Membership> findAll() {
        String sql = "SELECT * FROM memberships WHERE is_active = true";
        return jdbcTemplate.query(sql, new MembershipRowMapper());
    }

    public boolean buyMembership(TransactionsMembership transaction) {
        String sql = "INSERT INTO transactions_memberships (id_transaction, id_membership, id_member, order_time) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
        int rowsAffected = jdbcTemplate.update(sql, transaction.getId_transaction(), transaction.getId_membership(), transaction.getId_member());
        return rowsAffected > 0;
    }

    public Map<String, Object> prolongMembership(Integer memberId, Integer membershipId) {
        // Fetch the duration of the membership
        String fetchDurationSql = "SELECT duration FROM memberships WHERE id_membership = ?";
        Integer duration;
        try {
            duration = jdbcTemplate.queryForObject(fetchDurationSql, Integer.class, membershipId);
        } catch (DataAccessException e) {
            return Map.of("success", false, "message", "Membership duration not found.");
        }

        if (duration == null) {
            return Map.of("success", false, "message", "Membership duration is null.");
        }

        // Update the start date based on the duration
        String updateSql = "UPDATE client_membership SET start_date = CURRENT_DATE WHERE id_member = ? AND id_membership = ?";
        int rowsAffected = jdbcTemplate.update(updateSql, memberId, membershipId);

        if (rowsAffected > 0) {
            // Calculate the new expiration date
            String fetchNewStartDateSql = "SELECT start_date FROM client_membership WHERE id_member = ? AND id_membership = ?";
            Date newStartDate;
            try {
                newStartDate = jdbcTemplate.queryForObject(fetchNewStartDateSql, Date.class, memberId, membershipId);
            } catch (DataAccessException e) {
                return Map.of("success", false, "message", "Failed to fetch new start date.");
            }

            if (newStartDate == null) {
                return Map.of("success", false, "message", "New start date is null.");
            }

            // Calculate the new expiration date
            String fetchExpirationDateSql = "SELECT DATE_ADD(start_date, INTERVAL ? DAY) AS expiration_date FROM client_membership WHERE id_member = ? AND id_membership = ?";
            Date expirationDate;
            try {
                expirationDate = jdbcTemplate.queryForObject(fetchExpirationDateSql, Date.class, duration, memberId, membershipId);
            } catch (DataAccessException e) {
                return Map.of("success", false, "message", "Failed to fetch expiration date.");
            }

            return Map.of("success", true, "rowsAffected", rowsAffected, " ", expirationDate);
        } else {
            return Map.of("success", false, "message", "Failed to prolong membership.");
        }
    }

    private static class MembershipRowMapper implements RowMapper<Membership> {
        @Override
        public Membership mapRow(ResultSet rs, int rowNum) throws SQLException {
            Membership result = new Membership();
            result.setName(rs.getString("name"));
            result.setId_membership(rs.getInt("id_membership"));
            result.setIs_active(rs.getBoolean("is_active"));
            result.setPrice(rs.getBigDecimal("price"));
            result.setDuration(rs.getInt("duration"));
            return result;
        }
    }
}
