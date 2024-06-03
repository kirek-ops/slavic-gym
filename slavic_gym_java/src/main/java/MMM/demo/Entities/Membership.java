
package MMM.demo.Entities;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Membership {
    private String name;

    private Integer id_membership;

    private Boolean is_active;

    private BigDecimal price;

    private Integer duration;

}
