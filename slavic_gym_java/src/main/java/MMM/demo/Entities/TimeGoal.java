
package MMM.demo.Entities;

import java.time.Duration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeGoal {
    private Integer id_goal;

    private Integer id_member;

    private Integer id_exercise;

    private Integer minutes_target;

}
