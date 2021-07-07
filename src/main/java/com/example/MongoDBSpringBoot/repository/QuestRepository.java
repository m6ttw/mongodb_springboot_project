package com.example.MongoDBSpringBoot.repository;

import com.example.MongoDBSpringBoot.model.QuestDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestRepository extends MongoRepository<QuestDTO, String> {
}
