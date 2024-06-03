
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
public class TransactionsMemberships {
    private int id_transaction;
    private int id_membership;
    private int id_member;
    private ZonedDateTime order_time;
}
