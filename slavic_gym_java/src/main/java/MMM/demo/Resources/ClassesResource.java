package MMM.demo.Resources;

import MMM.demo.Daos.ClasseDaoImpl;
import MMM.demo.Entities.Classe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import MMM.demo.Utils.LocationFetcher;
import MMM.demo.Utils.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.SQLException;

@Slf4j
@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
public class ClassesResource {
    private final ClasseDaoImpl classeDaoImpl;

    @GetMapping("/{id}/get-classes-from-today")
    public ResponseEntity < List <Classe> > getAllClassesFromToday(@PathVariable Integer id) {
      return ResponseEntity.ok(classeDaoImpl.getAllFromToday(id));
    }

    @GetMapping("/{id}/{id_gym}/get-classes-gym-from-today")
    public ResponseEntity < List < Classe> > getAllClassesFromTodayInGym(@PathVariable Integer id, @PathVariable Integer id_gym) {
      List <Classe> result = classeDaoImpl.getAllFromTodayInGym(id, id_gym);
      return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}/submit-class")
    public ResponseEntity < String > submitClass(@PathVariable Integer id, @RequestBody Map < String, Object > requestBody) {
      Classe newClass = new Classe();

      try {
        newClass.setId_class(new UuidGenerator("id_classes").generateUniqueID());
      } catch (Exception e) {
        log.info("Error while geenrating id for class. Aborting");
        return ResponseEntity.notFound().build();
      }
      newClass.setClass_name((String) requestBody.get("class_name"));
      newClass.setSchedule(LocalDate.parse((String) requestBody.get("schedule")));
      newClass.setTime_from(LocalTime.parse((String) requestBody.get("time_from")));
      newClass.setTime_till(LocalTime.parse((String) requestBody.get("time_till")));
      newClass.setId_gym((Integer) requestBody.get("id_gym"));
      newClass.setCapacity((Integer) requestBody.get("capacity"));
      newClass.setId_instructor((Integer) requestBody.get("id_instructor"));

      log.info(
        "Creating {} {} {} {} {} {} {}",
        newClass.getId_class(),
        newClass.getSchedule(),
        newClass.getTime_from(),
        newClass.getTime_till(),
        newClass.getId_gym(),
        newClass.getCapacity(),
        newClass.getId_instructor()
      );

      try {
        int result = classeDaoImpl.insertClass(newClass);
        return ResponseEntity.ok("Successfully added class");
      } catch (Exception e) {
        log.info(e.getMessage());
        return ResponseEntity.ok(e.getMessage());
      }
    }
}
