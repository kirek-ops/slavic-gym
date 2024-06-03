package MMM.demo.Resources;

import MMM.demo.Daos.GymDaoImpl;
import MMM.demo.Entities.Gym;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/gyms")
@RequiredArgsConstructor
public class GymsResource {
    private final GymDaoImpl gymDaoImpl;

    @GetMapping("/getopengyms")
    ResponseEntity <ArrayList <Gym>> getOpenGyms () {
        return ResponseEntity.ok(new ArrayList<>(gymDaoImpl.findAll()));
    }
}
