package com.sagar.kafka.consumer.repository;

import com.sagar.kafka.consumer.entity.WikiMediaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikiMediaDataRepository extends JpaRepository<WikiMediaData, Long> {
}
