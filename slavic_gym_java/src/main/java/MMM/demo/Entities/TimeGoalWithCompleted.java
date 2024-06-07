package MMM.demo.Entities;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class TimeGoalWithCompleted {
  private Integer id_goal;
  private Integer id_member;
  private Integer id_exercise;
  private Integer minutes_target;

  private String name_exercise;
  private LocalDate completion_date;
}