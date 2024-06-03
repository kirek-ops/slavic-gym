
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
public class TimeGoals {
    private int id_goal;
    private int id_member;
    private int id_exercise;
    private Duration target_time;
}
