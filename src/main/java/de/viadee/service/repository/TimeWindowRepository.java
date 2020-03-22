package de.viadee.service.repository;

import de.viadee.service.entity.TimeWindowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeWindowRepository extends JpaRepository<TimeWindowEntity, Integer> {

}