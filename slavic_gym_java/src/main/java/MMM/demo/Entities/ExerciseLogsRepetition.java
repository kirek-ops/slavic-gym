
package MMM.demo.Entities;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseLogsRepetition {
    private Integer id_log;

    private Integer id_member;

    private Integer id_goal;

    private OffsetDateTime log_date;

    private Integer reps_done;

}
