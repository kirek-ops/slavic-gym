
package Repositories;

import Entities.Employees;
import java.util.List;

public interface EmployeesRepository {
    List<Employees> findAll();
    Employees findById();
    int addEmployees(Employees employees);
    int updateEmployees(Employees employees);
    int deleteEmployees();
}
