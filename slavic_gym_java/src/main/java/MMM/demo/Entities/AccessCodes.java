
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
public class AccessCodes {
    private String code_id;
    private ZonedDateTime generated_at;
    private int id_member;
    private ZonedDateTime first_used_at;
}
