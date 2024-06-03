
package MMM.demo.Repositories;

import MMM.demo.Entities.GymMembers;
import java.util.List;

public interface GymMembersRepository {
    List<GymMembers> findAll();
}
