
package Repositories;

import Entities.Visits;
import java.util.List;

public interface VisitsRepository {
    List<Visits> findAll();
    Visits findById(int id_member);
    int addVisits(Visits visits);
    int updateVisits(Visits visits);
    int deleteVisits(int id_member);
}
