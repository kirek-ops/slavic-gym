
package MMM.demo.Repositories;

import MMM.demo.Entities.Employees;
import java.util.List;

public interface EmployeesRepository {
    List<Employees> findAll();
}
