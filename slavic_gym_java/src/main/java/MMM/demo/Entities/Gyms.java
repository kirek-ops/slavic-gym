
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
public class Gyms {
    private int id_gym;
    private String name;
    private String street;
    private String city;
    private String postal_code;
    private String contact_number;
}
