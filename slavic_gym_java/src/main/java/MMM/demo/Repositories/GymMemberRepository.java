
package MMM.demo.Repositories;

import MMM.demo.Entities.GymMember;
import java.util.List;

public interface GymMemberRepository {
    List<GymMember> findAll();
}
