
package Repositories;

import Entities.TimeGoals;
import java.util.List;

public interface TimeGoalsRepository {
    List<TimeGoals> findAll();
    TimeGoals findById(int id_member, int id_exercise);
    int addTimeGoals(TimeGoals timeGoals);
    int updateTimeGoals(TimeGoals timeGoals);
    int deleteTimeGoals(int id_member, int id_exercise);
}
