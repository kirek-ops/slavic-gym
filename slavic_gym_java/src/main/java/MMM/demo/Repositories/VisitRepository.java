package MMM.demo.Repositories;

import MMM.demo.Entities.Visit;
import java.util.List;

public interface VisitRepository {
    List<Visit> findAll();
    List<Visit> findByMemberId(Integer id_member);  // Add this method
}
