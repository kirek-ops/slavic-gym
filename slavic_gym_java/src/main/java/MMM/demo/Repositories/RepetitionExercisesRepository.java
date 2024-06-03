
package Repositories;

import Entities.RepetitionExercises;
import java.util.List;

public interface RepetitionExercisesRepository {
    List<RepetitionExercises> findAll();
    RepetitionExercises findById();
    int addRepetitionExercises(RepetitionExercises repetitionExercises);
    int updateRepetitionExercises(RepetitionExercises repetitionExercises);
    int deleteRepetitionExercises();
}
