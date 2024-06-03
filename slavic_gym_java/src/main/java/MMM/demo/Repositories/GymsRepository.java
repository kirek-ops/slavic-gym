
package Repositories;

import Entities.Gyms;
import java.util.List;

public interface GymsRepository {
    List<Gyms> findAll();
    Gyms findById();
    int addGyms(Gyms gyms);
    int updateGyms(Gyms gyms);
    int deleteGyms();
}
