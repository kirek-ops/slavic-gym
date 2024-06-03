
package MMM.demo.Repositories;

import MMM.demo.Entities.Membership;
import java.util.List;

public interface MembershipRepository {
    List<Membership> findAll();
}
