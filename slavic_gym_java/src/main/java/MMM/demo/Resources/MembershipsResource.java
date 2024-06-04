package MMM.demo.Resources;

import MMM.demo.Daos.MembershipWithStartDateDaoImpl;
import MMM.demo.Entities.Membership;
import MMM.demo.Entities.MembershipWithStartDate;
import MMM.demo.Entities.TransactionsMembership;
import MMM.demo.Utils.UuidGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import MMM.demo.Daos.MembershipDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/memberships")
@RequiredArgsConstructor
public class MembershipsResource {
    private final MembershipDaoImpl membershipDaoImpl;
    private final MembershipWithStartDateDaoImpl membershipWithStartDateDaoImpl;

    @PostMapping("/getall")
    public ResponseEntity<List<Membership>> getAllMemberships() {
        List<Membership> memberships = membershipDaoImpl.findAll();
        log.info(new ArrayList<Membership>().toString());
        return ResponseEntity.ok(memberships);
    }

    @PostMapping("/buy")
    public ResponseEntity<Map<String, Object>> buyMembership(@RequestBody Map<String, Object> body) {
        TransactionsMembership transaction = new TransactionsMembership();
        transaction.setId_membership((Integer) body.get("membershipId"));
        transaction.setId_member((Integer) body.get("memberId"));

        try {
            transaction.setId_transaction(new UuidGenerator("id_transaction").generateUniqueID());
        } catch (Exception e) {
            log.info("No transaction id provided. Generating new one.");
        }

        boolean success = membershipDaoImpl.buyMembership(transaction);
        return ResponseEntity.ok(Map.of("success", success));
    }

    @PostMapping("/getbyid")
    public ResponseEntity<List<MembershipWithStartDate>> getMembershipsByMemberId(@RequestBody Map <String, Integer> body) {
        List<MembershipWithStartDate> memberships = membershipWithStartDateDaoImpl.findByMemberId(body.get("id"));
        return ResponseEntity.ok(memberships);
    }

    @PostMapping("/prolong")
    public ResponseEntity<Map<String, Object>> prolongMembership(@RequestBody Map<String, Object> body) {
        Integer memberId = (Integer) body.get("memberId");
        Integer membershipId = (Integer) body.get("membershipId");

        Map <String, Object> response = membershipDaoImpl.prolongMembership(memberId, membershipId);
        if (response.containsKey("message")) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(Map.of("success", true,
                                        "rowsAffected", response.get("rowsAffected"),
                                        "expirationDate", response.get("expirationDate")));
    }
}
