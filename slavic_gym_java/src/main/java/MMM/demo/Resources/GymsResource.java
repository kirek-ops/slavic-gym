package MMM.demo.Resources;

import MMM.demo.Daos.GymDaoImpl;
import MMM.demo.Entities.Gym;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Map;
import MMM.demo.Utils.LocationFetcher;

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

    @GetMapping("/{id}/get-location")
    ResponseEntity < Map <String, Double> > getCoordsById(@PathVariable String id) {
        Gym foundGym = gymDaoImpl.findById(Integer.parseInt(id));
        if (foundGym == null) {
            log.info("Gym was not found. Aborting.");
            return ResponseEntity.notFound().build();
        }
        try {
//            log.info("City {} Street {}", foundGym.getCity(), foundGym.getStreet());
            Double ar[] = LocationFetcher.getLatLng(foundGym.getCity(), foundGym.getStreet());
            return ResponseEntity.ok(Map.of("lat", ar[0], "lng", ar[1]));
        } catch (Exception e) {
            log.info("Exception occured while obtaining coordinates of the gym. Aborting.");
            return ResponseEntity.notFound().build();
        }
    }
}
