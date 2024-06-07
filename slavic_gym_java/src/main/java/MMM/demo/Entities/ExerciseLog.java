package MMM.demo.Entities;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

import MMM.demo.Entities.ExerciseLogRep;
import MMM.demo.Entities.ExerciseLogTime;

@Getter
@Setter
public class ExerciseLog {
  private String exercise_name;
  private LocalDate log_date;
  private Integer done;

  public static ExerciseLog of(ExerciseLogRep e) {
    ExerciseLog result = new ExerciseLog();
    result.setExercise_name(e.getExercise_name());
    result.setLog_date(e.getLog_date());
    result.setDone(e.getReps_done());
    return result;
  }

  public static ExerciseLog of(ExerciseLogTime e) {
    ExerciseLog result = new ExerciseLog();
    result.setExercise_name(e.getExercise_name());
    result.setLog_date(e.getLog_date());
    result.setDone(e.getMinutes_done());
    return result;
  }
}
