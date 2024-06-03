package MMM.demo.Resources;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthResource {
    @PostMapping("/login")
    public ResponseEntity <Map <String, Object>> login (@RequestParam String username) {
        return ResponseEntity.ok(Map.of("success", true,
                                        "id", "lol",
                                        "password", "lol"));
    }

    @PostMapping("/checkUserExists")
    public ResponseEntity <Map <String, Object>> checkUserExists (@RequestBody Map <String, Object> body) {
        return ResponseEntity.ok(Map.of("success", true));
    }

    @PostMapping("/signup")
    public ResponseEntity <?> signup (@RequestBody Map <String, Object> body) {
        return ResponseEntity.ok("lol");
    }
}