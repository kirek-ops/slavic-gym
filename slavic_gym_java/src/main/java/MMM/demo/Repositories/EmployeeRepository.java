
package MMM.demo.Repositories;

import MMM.demo.Entities.Employee;
import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAll();
}
