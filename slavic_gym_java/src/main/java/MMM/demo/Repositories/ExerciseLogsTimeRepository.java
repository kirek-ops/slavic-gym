
package Repositories;

import Entities.ExerciseLogsTime;
import java.util.List;

public interface ExerciseLogsTimeRepository {
    List<ExerciseLogsTime> findAll();
    ExerciseLogsTime findById(int id_member, int id_goal);
    int addExerciseLogsTime(ExerciseLogsTime exerciseLogsTime);
    int updateExerciseLogsTime(ExerciseLogsTime exerciseLogsTime);
    int deleteExerciseLogsTime(int id_member, int id_goal);
}
