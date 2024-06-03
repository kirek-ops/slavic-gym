
package MMM.demo.Entities;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionsMembership {
    private Integer id_transaction;

    private Integer id_membership;

    private Integer id_member;

    private OffsetDateTime order_time;

}
