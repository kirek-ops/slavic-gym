
package MMM.demo.Repositories;

import MMM.demo.Entities.Positions;
import java.util.List;

public interface PositionsRepository {
    List<Positions> findAll();
}
