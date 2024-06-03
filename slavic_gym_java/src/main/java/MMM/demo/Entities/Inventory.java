
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
public class Inventory {
    private int id_item;
    private String item_name;
    private int quantity;
    private int id_gym;
    private int id_category;
}
