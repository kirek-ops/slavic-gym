
package Repositories;

import Entities.RepetitionGoals;
import java.util.List;

public interface RepetitionGoalsRepository {
    List<RepetitionGoals> findAll();
    RepetitionGoals findById(int id_member, int id_exercise);
    int addRepetitionGoals(RepetitionGoals repetitionGoals);
    int updateRepetitionGoals(RepetitionGoals repetitionGoals);
    int deleteRepetitionGoals(int id_member, int id_exercise);
}
