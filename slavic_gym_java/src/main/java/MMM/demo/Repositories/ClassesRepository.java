
package Repositories;

import Entities.Classes;
import java.util.List;

public interface ClassesRepository {
    List<Classes> findAll();
    Classes findById(int id_gym, int id_instructor);
    int addClasses(Classes classes);
    int updateClasses(Classes classes);
    int deleteClasses(int id_gym, int id_instructor);
}
