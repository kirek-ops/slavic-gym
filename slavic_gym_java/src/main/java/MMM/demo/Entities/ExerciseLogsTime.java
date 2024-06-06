
package MMM.demo.Entities;

import java.time.Duration;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseLogsTime {
    private Integer id_log;

    private Integer id_member;

    private Integer id_exercise;

    private LocalDate log_date;

    private Integer minutes_done;

}
