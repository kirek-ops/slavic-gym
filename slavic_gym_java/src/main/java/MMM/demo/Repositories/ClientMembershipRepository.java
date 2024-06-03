
package MMM.demo.Repositories;

import MMM.demo.Entities.ClientMembership;
import java.util.List;

public interface ClientMembershipRepository {
    List<ClientMembership> findAll();
}
