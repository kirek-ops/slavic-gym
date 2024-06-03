
package Repositories;

import Entities.EmployeePositions;
import java.util.List;

public interface EmployeePositionsRepository {
    List<EmployeePositions> findAll();
    EmployeePositions findById(int id_employee, int id_position);
    int addEmployeePositions(EmployeePositions employeePositions);
    int updateEmployeePositions(EmployeePositions employeePositions);
    int deleteEmployeePositions(int id_employee, int id_position);
}
