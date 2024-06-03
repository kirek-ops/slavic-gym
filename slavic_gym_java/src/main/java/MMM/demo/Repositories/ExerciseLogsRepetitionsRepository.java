
package Repositories;

import Entities.ExerciseLogsRepetitions;
import java.util.List;

public interface ExerciseLogsRepetitionsRepository {
    List<ExerciseLogsRepetitions> findAll();
    ExerciseLogsRepetitions findById(int id_member, int id_goal);
    int addExerciseLogsRepetitions(ExerciseLogsRepetitions exerciseLogsRepetitions);
    int updateExerciseLogsRepetitions(ExerciseLogsRepetitions exerciseLogsRepetitions);
    int deleteExerciseLogsRepetitions(int id_member, int id_goal);
}
