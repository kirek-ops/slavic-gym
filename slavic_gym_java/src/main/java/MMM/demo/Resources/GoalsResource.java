package MMM.demo.Resources;

import MMM.demo.Daos.TimeGoalDaoImpl;
import MMM.demo.Daos.RepetitionGoalDaoImpl;

import MMM.demo.Entities.TimeGoal;
import MMM.demo.Entities.RepetitionGoal;

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

@RestController
@RequestMapping("/goals")
@RequiredArgsConstructor
@Slf4j
public class GoalsResource {
  private final RepetitionGoalDaoImpl repetitionGoalDaoImpl;
  private final TimeGoalDaoImpl timeGoalDaoImpl;

  @PostMapping("/submit-goal/{id}")
  public ResponseEntity < String > submitExercise(@PathVariable Integer id, @RequestBody Map <String, Object> requestBody) {
    String type = (String)requestBody.get("type");
    if (type.equals("time")) {
      TimeGoal curGoal = new TimeGoal();

      try {
        curGoal.setId_goal(new UuidGenerator("id_time_goal").generateUniqueID());
      } catch (Exception e) {
        log.info("Error while generating id for goal. Aborting");
        return ResponseEntity.badRequest().body("Error while generating id");
      }

      curGoal.setId_member(id);
      curGoal.setId_exercise((Integer)requestBody.get("exerciseId"));
      curGoal.setMinutes_target((Integer)requestBody.get("value"));

      log.info("Created time exercise log: {} {} {} {}", curGoal.getId_goal(), curGoal.getId_member(), curGoal.getMinutes_target());

      try {
        timeGoalDaoImpl.insertGoal(curGoal);
      } catch (Exception e) {
        log.info(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
      }
    }
    else {
      RepetitionGoal curGoal = new RepetitionGoal();

      try {
        curGoal.setId_goal(new UuidGenerator("id_rep_goal").generateUniqueID());
      } catch (Exception e) {
        log.info("Error while generating id for log. Aborting");
        return ResponseEntity.badRequest().body("Error while generating id");
      }

      curGoal.setId_member(id);
      curGoal.setId_exercise((Integer)requestBody.get("exerciseId"));
      curGoal.setReps_target((Integer)requestBody.get("value"));

      log.info("Created time exercise log: {} {} {} {}", curGoal.getId_goal(), curGoal.getId_member(), curGoal.getReps_target());

      try {
        repetitionGoalDaoImpl.insertGoal(curGoal);
      } catch (Exception e) {
        log.info(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
      }
    }
    return null;
  }

}
