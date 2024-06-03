
package Repositories;

import Entities.Inventory;
import java.util.List;

public interface InventoryRepository {
    List<Inventory> findAll();
    Inventory findById(int id_gym, int id_category);
    int addInventory(Inventory inventory);
    int updateInventory(Inventory inventory);
    int deleteInventory(int id_gym, int id_category);
}
