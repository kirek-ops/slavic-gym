
package MMM.demo.Entities;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.Duration;
import java.time.LocalTime;
import java.math.BigDecimal;

@Getter
@Setter
public class ExerciseLogsRepetitions {
    private int id_log;
    private int id_member;
    private int id_goal;
    private ZonedDateTime log_date;
    private int reps_done;
}
