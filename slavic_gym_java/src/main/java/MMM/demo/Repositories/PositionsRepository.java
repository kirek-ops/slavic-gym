
package Repositories;

import Entities.Positions;
import java.util.List;

public interface PositionsRepository {
    List<Positions> findAll();
    Positions findById();
    int addPositions(Positions positions);
    int updatePositions(Positions positions);
    int deletePositions();
}
