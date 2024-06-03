
package Repositories;

import Entities.TimeExercises;
import java.util.List;

public interface TimeExercisesRepository {
    List<TimeExercises> findAll();
    TimeExercises findById();
    int addTimeExercises(TimeExercises timeExercises);
    int updateTimeExercises(TimeExercises timeExercises);
    int deleteTimeExercises();
}
