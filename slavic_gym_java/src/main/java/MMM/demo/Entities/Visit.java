
package MMM.demo.Entities;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Visit {
    private Integer id_visit;

    private Integer id_member;

    private OffsetDateTime visit_time;

}
