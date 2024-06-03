
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
public class GymMembers {
    private int id_member;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private LocalDate join_date;
    private String passwd;
}
