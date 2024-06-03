
package Repositories;

import Entities.TransactionsInventory;
import java.util.List;

public interface TransactionsInventoryRepository {
    List<TransactionsInventory> findAll();
    TransactionsInventory findById(int id_item, int id_member);
    int addTransactionsInventory(TransactionsInventory transactionsInventory);
    int updateTransactionsInventory(TransactionsInventory transactionsInventory);
    int deleteTransactionsInventory(int id_item, int id_member);
}
