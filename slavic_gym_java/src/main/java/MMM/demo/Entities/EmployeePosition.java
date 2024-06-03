
package MMM.demo.Entities;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeePosition {
    private Integer id_employee;

    private Integer id_position;

    private LocalDate start_date;

    private LocalDate end_date;

}
