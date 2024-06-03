
package MMM.demo.Repositories;

import MMM.demo.Entities.TransactionsMembership;
import java.util.List;

public interface TransactionsMembershipRepository {
    List<TransactionsMembership> findAll();
}
