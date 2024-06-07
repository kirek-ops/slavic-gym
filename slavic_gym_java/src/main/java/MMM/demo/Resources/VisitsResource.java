package MMM.demo.Resources;

import MMM.demo.Entities.Visit;
import MMM.demo.Repositories.VisitRepository;
import MMM.demo.Utils.UuidGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/visits")
@RequiredArgsConstructor
public class VisitsResource {

    private final VisitRepository visitRepository;

    @GetMapping("/member/{id_member}")
    public ResponseEntity<List<Visit>> getVisitsByMember(@PathVariable Integer id_member) {
        log.info("Received request to get visits for member with id {}", id_member);
        List<Visit> visits = visitRepository.findByMemberId(id_member);
        log.info("Found {} visits for member with id {}", visits.size(), id_member);
        if (visits.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(visits);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addVisit(@RequestBody VisitRequest visitRequest) {
        Integer memberId = visitRequest.getMemberId();
        OffsetDateTime now = OffsetDateTime.now();

        log.info("Received request to add visit for member with id {}", memberId);

        OffsetDateTime visitTime = visitRepository.findLastVisitTimeByMemberId(memberId);
        if (visitTime != null) {
            if (ChronoUnit.HOURS.between(visitTime, now) < 3) {
                return ResponseEntity.ok("You can get a new QR code only after 3 hours from your last visit.");
            }
        }

        try {
            Integer newId = new UuidGenerator("id_visit").generateUniqueID();
            Visit newVisit = new Visit();
            newVisit.setId_visit(newId);
            newVisit.setId_member(memberId);
            newVisit.setVisit_time(now);

            visitRepository.save(newVisit);
            return ResponseEntity.ok("Visit added successfully.");
        } catch (Exception e) {
            log.error("Error generating unique ID for visit", e);
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    public static class VisitRequest {
        private Integer memberId;

        public Integer getMemberId() {
            return memberId;
        }

        public void setMemberId(Integer memberId) {
            this.memberId = memberId;
        }
    }
}
