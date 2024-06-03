package MMM.demo.Resources;

import MMM.demo.Entities.Visit;
import MMM.demo.Repositories.VisitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
