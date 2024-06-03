
package MMM.demo.Entities;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessCode {
    private String code_id;

    private OffsetDateTime generated_at;

    private Integer id_member;

    private OffsetDateTime first_used_at;

}
