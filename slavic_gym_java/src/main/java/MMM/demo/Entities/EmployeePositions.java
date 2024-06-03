
package Entities;

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
public class EmployeePositions {
    private int id_employee;
    private int id_position;
    private LocalDate start_date;
    private LocalDate end_date;
}
