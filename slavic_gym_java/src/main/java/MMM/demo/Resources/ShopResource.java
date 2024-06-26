package MMM.demo.Resources;

import MMM.demo.Daos.CategorieDaoImpl;
import MMM.demo.Daos.ImagesDaoImpl;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Slf4j
@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopResource {
    private final InventoryDaoImpl inventoryDao;
    private final TransactionsInventoryDaoImpl transactionsInventoryDao;
    private final ImagesDaoImpl imagesDao;
    private final CategorieDaoImpl categorieDao;

    @PostMapping("/getallbyid")
    public ResponseEntity <Map <Integer, Map <String, Object>>> getAllByGymId (@RequestBody Map<String, Object> body) {
        List <Inventory> inventory = inventoryDao.findAllByGymId((Integer) body.get("intGym"));
        Map <Integer, Map <String, Object>> inventoryImages = new HashMap<>();
        for (Inventory item : inventory) {
            Map <String, Object> itemMap = new HashMap<>();
            itemMap.put("id_item", item.getId_item());
            itemMap.put("item_name", item.getItem_name());
            itemMap.put("quantity", item.getQuantity());
            itemMap.put("price", item.getPrice());
            itemMap.put("id_gym", item.getId_gym());
            Integer id_image = imagesDao.findImageIdByItemId(item.getId_item());
            log.info("Image id " + id_image);
            try {
                byte[] bytes = Files.readAllBytes(Paths.get("slavic_gym_java/src/main/resources/static/images/" +
                        id_image.toString() + ".png"));
                itemMap.put("image", bytes);
            } catch (Exception e) {
                log.error("Error loading image for item " + item.getId_item());
            }
            inventoryImages.put(item.getId_item(), itemMap);
        }
        return ResponseEntity.ok(inventoryImages);
    }

    @PostMapping("/getbyiditem")
    public ResponseEntity <Inventory> getByItemId (@RequestBody Map<String, Object> body) {
        log.info("Body " + (Integer) body.get("itemId") + " " + (Integer) body.get("gymId"));
        Inventory inventory = inventoryDao.findByItemId((Integer) body.get("itemId"), (Integer) body.get("gymId"));
        log.info("Inventory " + inventory);
        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/getallcategories")
    public ResponseEntity <List <Categorie>> getCategories () {
        return ResponseEntity.ok(categorieDao.findAll());
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

    @PostMapping("/additem")
    public ResponseEntity<String> addProduct(@RequestParam("item_name") String item_name,
                                             @RequestParam("quantity") Integer quantity,
                                             @RequestParam("id_gym") Integer id_gym,
                                             @RequestParam("price") Float price,
                                             @RequestParam("image") MultipartFile file,
                                             @RequestParam("id_category") Integer category) {

        Inventory inventory = new Inventory();
        inventory.setId_gym(id_gym);
        inventory.setItem_name(item_name);
        inventory.setQuantity(quantity);
        inventory.setPrice(price);

        try {
            Integer id = new UuidGenerator("id_item").generateUniqueID();
            inventory.setId_item(id);
            inventoryDao.insertProduct(inventory, category);

            Path path = Paths.get("src/main/resources/static/images/" + id + ".png");
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            log.error("Error" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating id for item");
        }

        return null;
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
                transactionsInventoryDao.insertTransaction(transaction);
            }
            return ResponseEntity.ok(Map.of("success", true));
        } catch (Exception e) {
            log.error("Error buying items: " + last);
            return ResponseEntity.ok(Map.of("success", false,
                                            "erroredItem", last));
        }
    }

}
