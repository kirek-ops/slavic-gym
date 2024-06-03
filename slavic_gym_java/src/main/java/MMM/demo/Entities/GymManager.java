
package MMM.demo.Entities;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GymManager {
    private Integer id_gym;

    private Integer id_manager;

    private LocalDate start_date;

    private LocalDate end_date;

}
