package MMM.demo.Resources;

import MMM.demo.Daos.CategorieDaoImpl;
import MMM.demo.Daos.InventoryDaoImpl;
import MMM.demo.Daos.TransactionsInventoryDaoImpl;
import MMM.demo.Entities.Categorie;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopResource {
    private final InventoryDaoImpl inventoryDao;
    private final TransactionsInventoryDaoImpl transactionsInventoryDao;
    private final CategorieDaoImpl categorieDao;

    @PostMapping("/getallbyid")
    public ResponseEntity <List<Inventory>> getAllByGymId (@RequestBody Map<String, Object> body) {
        List <Inventory> inventory = inventoryDao.findAllByGymId((Integer) body.get("intGym"));
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/getbyiditem")
    public ResponseEntity <Inventory> getByItemId (@RequestBody Map<String, Object> body) {
        log.info("Body " + (Integer) body.get("itemId") + " " + (Integer) body.get("gymId"));
        Inventory inventory = inventoryDao.findByItemId((Integer) body.get("itemId"), (Integer) body.get("gymId"));
        log.info("Inventory " + inventory);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/getcategories")
        public ResponseEntity <Map <Integer, Categorie>> getCategories (@RequestBody List <Integer> ids) {
        Map <Integer, Categorie> categories = new HashMap<>();
        for (Integer id : ids) {
            Categorie categorie = categorieDao.findById(id);
            categories.put(id, categorie);
        }
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/buy")
    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<Map <String, Object>> buy(@RequestBody Map<String, Object> body) {
        log.info("Buying items: " + body.toString());
        Integer last = null;
        try {
            Integer id = Integer.parseInt(body.get("id").toString());
            Integer gym = Integer.parseInt(body.get("gym").toString());
            ArrayList<Map<String, Object>> items = (ArrayList<Map<String, Object>>) body.get("items");

            // Begin the transaction
            for (Map<String, Object> item : items) {
                Integer itemId = Integer.parseInt(item.get("id_item").toString());
                Integer quantity = Integer.parseInt((item.get("quantity")).toString());
                last = itemId;

                TransactionsInventory transaction = new TransactionsInventory();
                transaction.setId_transaction(new UuidGenerator("id_transaction").generateUniqueID());
                transaction.setId_item(itemId);
                transaction.setId_member(id);
                transaction.setOrder_time(OffsetDateTime.now());
                transaction.setQuantity(quantity);
                if (transactionsInventoryDao.insertTransaction(transaction) == 0) {
                    throw new Exception("Failed to insert transaction");
                }
            }
            return ResponseEntity.ok(Map.of("success", true));
        } catch (Exception e) {
            log.error("Error buying items: " + last);
            return ResponseEntity.ok(Map.of("success", false,
                                            "erroredItem", last));
        }
    }

}
