package MMM.demo.Resources;

import MMM.demo.Daos.InventoryDaoImpl;
import MMM.demo.Daos.TransactionsInventoryDaoImpl;
import MMM.demo.Entities.Inventory;
import MMM.demo.Entities.TransactionsInventory;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> buy(@RequestBody Map<String, Object> body) {
        log.info("Body" + body);

        Integer id = Integer.parseInt(body.get("id").toString());
        Integer gym = Integer.parseInt(body.get("gym").toString());
        ArrayList <Map <Integer, Integer>> items = (ArrayList <Map <Integer, Integer>>) body.get("items");

        log.info("ID: " + id);
        log.info("Gym: " + gym);
        log.info("Items: " + items);

        return null;
    }
}
