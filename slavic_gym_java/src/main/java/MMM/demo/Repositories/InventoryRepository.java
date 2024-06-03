
package MMM.demo.Repositories;

import MMM.demo.Entities.Inventory;
import java.util.List;

public interface InventoryRepository {
    List<Inventory> findAll();
}
