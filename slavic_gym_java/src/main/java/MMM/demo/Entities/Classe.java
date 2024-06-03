
package MMM.demo.Entities;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Classe {
    private Integer id_class;

    private String class_name;

    private LocalDateTime schedule;

    private LocalTime time_from;

    private LocalTime time_till;

    private Integer id_gym;

    private Integer capacity;

    private Integer id_instructor;

}
