package MMM.demo.Resources;

import MMM.demo.Daos.GymMemberDaoImpl;
import MMM.demo.Entities.Gym;
import MMM.demo.Entities.GymMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import MMM.demo.Utils.EmailValidator;
import MMM.demo.Utils.PhoneValidator;

import java.time.LocalDate;
import java.util.Map;

import MMM.demo.Utils.UuidGenerator;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthResource {
    private final GymMemberDaoImpl gymMemberDaoImpl;

    @PostMapping("/login")
    public ResponseEntity <Map <String, Object>> login (@RequestParam String email) {
        try {
            GymMember user = gymMemberDaoImpl.findByEmail(email);
            return ResponseEntity.ok(Map.of("success", true,
                                            "id", user.getId_member(),
                                            "correctPassword", user.getPasswd()));
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.ok(Map.of("success", false, "message", "Incorrect email or password"));
        }
    }

    @PostMapping("/checkUserExists")
    public ResponseEntity <Map <String, Object>> checkUserExists (@RequestBody Map <String, Object> body) {
        return ResponseEntity.ok(Map.of(
            "success", 
            gymMemberDaoImpl.existsMember((String)body.get("email"), (String)body.get("phone"))
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity <Map <String, Object>> signup (@RequestBody Map <String, Object> body) {
        GymMember user = new GymMember();

        log.info("Received request to create new user: {}", body.toString());

        if (!EmailValidator.isValidEmail((String) body.get("email"))) {
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid email"));
        }

        if (!PhoneValidator.isValidPhone((String) body.get("phone"))) {
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid phone number"));
        }

        user.setEmail((String) body.get("email"));
        user.setFirst_name((String) body.get("firstName"));
        user.setLast_name((String) body.get("secondName"));
        user.setPhone_number((String) body.get("phone"));
        user.setJoin_date(LocalDate.now());
        user.setPasswd((String) body.get("password"));

        try {
            user.setId_member(new UuidGenerator("id_sequence").generateUniqueID());
        } catch (Exception e) {
            log.info("Error while assigning id. Aborting.");
            return ResponseEntity.notFound().build();
        }

        gymMemberDaoImpl.createGymMember(user);

        return ResponseEntity.ok(Map.of("id", user.getId_member()));

    }
}