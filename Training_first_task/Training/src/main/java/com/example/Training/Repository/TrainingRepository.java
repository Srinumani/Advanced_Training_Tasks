package com.example.Training.Repository;



import com.example.Training.Entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    // Query to find ongoing training
    @Query("SELECT t FROM Training t WHERE t.startDate <= CURRENT_DATE AND t.endDate >= CURRENT_DATE")
    List<Training> findOngoingTrainings();

    // Query to find training by skill
    @Query("SELECT t FROM Training t JOIN t.skills s WHERE s = :skill")
    List<Training> findTrainingsBySkill(String skill);
}

