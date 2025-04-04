package com.example.Training.Service;

import com.example.Training.Entity.Training;
import com.example.Training.Repository.TrainingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {
    private final TrainingRepository trainingRepository;

    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    // ✅ Get all trainings
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    // ✅ Get training by ID
    public Training getTrainingById(Long id) {
        return trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found with ID: " + id));
    }

    // ✅ Add a new training
    public Training addTraining(Training training) {
        return trainingRepository.save(training);
    }

    // ✅ Update training by ID
    public Training updateTraining(Long id, Training updatedTraining) {
        Optional<Training> existingTrainingOptional = trainingRepository.findById(id);

        if (existingTrainingOptional.isPresent()) {
            Training existingTraining = existingTrainingOptional.get();

            // Update fields
            existingTraining.setName(updatedTraining.getName());
            existingTraining.setSkills(updatedTraining.getSkills());
            existingTraining.setDuration(updatedTraining.getDuration());
            existingTraining.setStartDate(updatedTraining.getStartDate());
            existingTraining.setEndDate(updatedTraining.getEndDate());
            existingTraining.setNumberOfBatches(updatedTraining.getNumberOfBatches());
            existingTraining.setStudentsPerBatch(updatedTraining.getStudentsPerBatch());
            existingTraining.setBudget(updatedTraining.getBudget());
            existingTraining.setTableOfContent(updatedTraining.getTableOfContent());
            existingTraining.setOrganizationName(updatedTraining.getOrganizationName());
            existingTraining.setPoc(updatedTraining.getPoc());
            existingTraining.setEmail(updatedTraining.getEmail());
            existingTraining.setPhone(updatedTraining.getPhone());
            existingTraining.setStudentType(updatedTraining.getStudentType());
            existingTraining.setStatus(updatedTraining.getStatus());

            return trainingRepository.save(existingTraining);
        } else {
            throw new RuntimeException("Training not found with ID: " + id);
        }
    }

    // ✅ Delete training by ID
    public void deleteTraining(Long id) {
        if (trainingRepository.existsById(id)) {
            trainingRepository.deleteById(id);
        } else {
            throw new RuntimeException("Training not found with ID: " + id);
        }
    }

    // ✅ Get ongoing trainings
    public List<Training> getOngoingTrainings() {
        return trainingRepository.findOngoingTrainings();
    }

    // ✅ Get trainings by skill
    public List<Training> getTrainingsBySkill(String skill) {
        return trainingRepository.findTrainingsBySkill(skill);
    }
}
