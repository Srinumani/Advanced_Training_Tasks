package com.ust.normalspringboot.repository;
import com.ust.normalspringboot.models.DataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<DataModel, Long> {
}
