
package MMM.demo.Repositories;

import MMM.demo.Entities.MemberEmployee;
import java.util.List;

public interface MemberEmployeeRepository {
    List<MemberEmployee> findAll();
}
