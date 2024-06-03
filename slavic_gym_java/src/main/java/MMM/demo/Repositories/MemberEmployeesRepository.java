
package Repositories;

import Entities.MemberEmployees;
import java.util.List;

public interface MemberEmployeesRepository {
    List<MemberEmployees> findAll();
    MemberEmployees findById(int id_member, int id_employee);
    int addMemberEmployees(MemberEmployees memberEmployees);
    int updateMemberEmployees(MemberEmployees memberEmployees);
    int deleteMemberEmployees(int id_member, int id_employee);
}
