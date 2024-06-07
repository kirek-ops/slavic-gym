package MMM.demo.Resources;

import MMM.demo.Daos.RepetitionExerciseDaoImpl;
import MMM.demo.Daos.TimeExerciseDaoImpl;
import MMM.demo.Entities.RepetitionExercise;
import MMM.demo.Entities.TimeExercise;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import MMM.demo.Daos.ClasseDaoImpl;
import MMM.demo.Entities.Classe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import MMM.demo.Utils.LocationFetcher;
import MMM.demo.Utils.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/exercises")
@RequiredArgsConstructor
@Slf4j
public class ExerciseResource {
    private final TimeExerciseDaoImpl timeExerciseDaoImpl;
    private final RepetitionExerciseDaoImpl repetitionExerciseDaoImpl;

    @GetMapping("/time-exercises")
    public ResponseEntity< List <TimeExercise> > getAllTimeExercises() {
        return ResponseEntity.ok(timeExerciseDaoImpl.findAll());
    }

    @GetMapping("/rep-exercises")
    public ResponseEntity < List <RepetitionExercise> > getAllRepetitionExercises() {
        return ResponseEntity.ok(repetitionExerciseDaoImpl.findAll());
    }
}
