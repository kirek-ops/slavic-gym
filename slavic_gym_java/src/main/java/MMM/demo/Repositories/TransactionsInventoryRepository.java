
package MMM.demo.Repositories;

import MMM.demo.Entities.TransactionsInventory;
import java.util.List;

public interface TransactionsInventoryRepository {
    List<TransactionsInventory> findAll();
}
