package com.example.MongoDBSpringBoot.controller;

import com.example.MongoDBSpringBoot.model.QuestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.MongoDBSpringBoot.repository.QuestRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class QuestController {

    @Autowired
    private QuestRepository questRepository;

    @GetMapping(value = "/quests")
    public ResponseEntity<?> getAllQuests() {
        List<QuestDTO> quests = questRepository.findAll();
        if (quests.size() > 0) {
            return new ResponseEntity<List<QuestDTO>>(quests, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No active quests", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/quests")
    public ResponseEntity<?> createQuest(@RequestBody QuestDTO quest) {
        try {
            quest.setCreatedAt(new Date(System.currentTimeMillis()));
            questRepository.save(quest);
            return new ResponseEntity<QuestDTO>(quest, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/quests/{id}")
    public ResponseEntity<?> getSingleQuest(@PathVariable("id") String id) {
        Optional<QuestDTO> questOptional = questRepository.findById(id);
        if (questOptional.isPresent()) {
            return new ResponseEntity<>(questOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Quest not found with id " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/quests/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody QuestDTO quest) {
        Optional<QuestDTO> questOptional = questRepository.findById(id);
        if (questOptional.isPresent()) {
            QuestDTO questToSave = questOptional.get();
            questToSave.setCompleted(quest.getCompleted() != null ? quest.getCompleted() : questToSave.getCompleted());
            questToSave.setQuest(quest.getQuest() != null ? quest.getQuest() : questToSave.getQuest());
            questToSave.setDescription(quest.getDescription() != null ? quest.getDescription() : questToSave.getDescription());
            questToSave.setUpdatedAt(new Date(System.currentTimeMillis()));
            questRepository.save(questToSave);
            return new ResponseEntity<>(questToSave, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Quest not found with id " + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/quests/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id){
        try {
            questRepository.deleteById(id);
            return new ResponseEntity<>("Successfully deleted with id " + id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

