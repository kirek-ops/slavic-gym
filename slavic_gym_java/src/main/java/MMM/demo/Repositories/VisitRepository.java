package MMM.demo.Repositories;

import MMM.demo.Entities.Visit;

import java.time.OffsetDateTime;
import java.util.List;

public interface VisitRepository {
    List<Visit> findAll();
    List<Visit> findByMemberId(Integer id_member);  // Add this method
    void addVisit(Integer id_member, OffsetDateTime visit_time);  // Add this method
    OffsetDateTime findLastVisitTimeByMemberId(Integer id_member);  // Add this method
    void save(Visit visit);  // Add this method
}
