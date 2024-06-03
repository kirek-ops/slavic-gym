
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
public class Classes {
    private String class_name;
    private LocalDateTime schedule;
    private LocalTime time_from;
    private LocalTime time_till;
    private int id_gym;
    private int capacity;
    private int id_instructor;
}
