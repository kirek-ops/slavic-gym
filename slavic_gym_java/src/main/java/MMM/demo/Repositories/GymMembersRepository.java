
package Repositories;

import Entities.GymMembers;
import java.util.List;

public interface GymMembersRepository {
    List<GymMembers> findAll();
    GymMembers findById();
    int addGymMembers(GymMembers gymMembers);
    int updateGymMembers(GymMembers gymMembers);
    int deleteGymMembers();
}
