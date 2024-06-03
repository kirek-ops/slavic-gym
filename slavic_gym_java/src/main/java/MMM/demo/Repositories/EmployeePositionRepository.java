
package MMM.demo.Repositories;

import MMM.demo.Entities.EmployeePosition;
import java.util.List;

public interface EmployeePositionRepository {
    List<EmployeePosition> findAll();
}
