
package Repositories;

import Entities.GymManagers;
import java.util.List;

public interface GymManagersRepository {
    List<GymManagers> findAll();
    GymManagers findById(int id_gym, int id_manager);
    int addGymManagers(GymManagers gymManagers);
    int updateGymManagers(GymManagers gymManagers);
    int deleteGymManagers(int id_gym, int id_manager);
}
