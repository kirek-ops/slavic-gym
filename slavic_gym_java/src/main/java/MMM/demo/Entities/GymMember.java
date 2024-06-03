
package MMM.demo.Entities;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GymMember {
    private Integer id_member;

    private String first_name;

    private String last_name;

    private String email;

    private String phone_number;

    private LocalDate join_date;

    private String passwd;

}
