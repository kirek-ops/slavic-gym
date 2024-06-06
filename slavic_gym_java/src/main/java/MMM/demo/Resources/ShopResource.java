package MMM.demo.Resources;

import MMM.demo.Daos.InventoryDaoImpl;
import MMM.demo.Daos.TransactionsInventoryDaoImpl;
import MMM.demo.Entities.Inventory;
import MMM.demo.Entities.TransactionsInventory;
import MMM.demo.Utils.UuidGenerator;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopResource {
    private final InventoryDaoImpl inventoryDao;
    private final TransactionsInventoryDaoImpl transactionsInventoryDao;

    @PostMapping("/getallbyid")
    public ResponseEntity <List<Inventory>> getAllByGymId (@RequestBody Map<String, Object> body) {
        List <Inventory> inventory = inventoryDao.findAllByGymId((Integer) body.get("intGym"));
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/buy")
    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<String> buy(@RequestBody Map<String, Object> body) {
        log.info("Buying items: " + body.toString());
        try {
            Integer id = Integer.parseInt(body.get("id").toString());
            Integer gym = Integer.parseInt(body.get("gym").toString());
            ArrayList<Map<String, Object>> items = (ArrayList<Map<String, Object>>) body.get("items");


            // Begin the transaction
            for (Map<String, Object> item : items) {
                Integer itemId = Integer.parseInt(item.get("id_item").toString());
                log.info(itemId.toString());
                Integer quantity = Integer.parseInt((item.get("quantity")).toString());
                log.info(quantity.toString());
                log.info("Item: " + itemId + " Quantity: " + quantity);

                // Attempt to insert a new transaction for each item
                log.info("Inserting transaction for item: " + itemId + " with quantity: " + quantity);
                TransactionsInventory transaction = new TransactionsInventory();
                transaction.setId_transaction(new UuidGenerator("id_transaction").generateUniqueID());
                transaction.setId_item(itemId);
                transaction.setId_member(id);
                transaction.setOrder_time(OffsetDateTime.now());
                transaction.setQuantity(quantity);
                log.info("Transaction created: " + transaction.toString() + " " + transaction.getId_transaction());
                transactionsInventoryDao.insertTransaction(transaction);
                log.info("Transaction inserted: " + transaction.toString());
//                log.info(item.get("quantity").toString());
            }
//            // Commit the transaction
            return ResponseEntity.ok("Items successfully bought!");
        } catch (Exception e) {
            log.error("Error buying items: " + e.getMessage());
            // Rollback the transaction in case of an error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error buying items: " + e.getMessage());
        }
    }

}
