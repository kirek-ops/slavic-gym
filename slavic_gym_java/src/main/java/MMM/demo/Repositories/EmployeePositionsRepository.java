
package MMM.demo.Repositories;

import MMM.demo.Entities.EmployeePositions;
import java.util.List;

public interface EmployeePositionsRepository {
    List<EmployeePositions> findAll();
}
