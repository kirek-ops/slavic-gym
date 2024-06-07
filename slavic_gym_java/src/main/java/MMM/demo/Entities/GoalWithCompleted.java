package MMM.demo.Entities;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

import MMM.demo.Entities.TimeGoalWithCompleted;
import MMM.demo.Entities.RepGoalWithCompleted;

@Getter
@Setter
public class GoalWithCompleted {
  private String exercise_name;
  private Integer target;
  private LocalDate completion;

  public static GoalWithCompleted of(TimeGoalWithCompleted obj) {
    GoalWithCompleted result = new GoalWithCompleted();
    result.setExercise_name(obj.getName_exercise());
    result.setTarget(obj.getMinutes_target());
    result.setCompletion(obj.getCompletion_date());
    return result;
  }

  public static GoalWithCompleted of(RepGoalWithCompleted obj) {
    GoalWithCompleted result = new GoalWithCompleted();
    result.setExercise_name(obj.getName_exercise());
    result.setTarget(obj.getReps_target());
    result.setCompletion(obj.getCompletion_date());
    return result;
  }
}
