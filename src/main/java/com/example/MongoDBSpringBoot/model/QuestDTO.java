package com.example.MongoDBSpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "quests")
public class QuestDTO {

    @Id
    private String id;

    private String quest;

    private String giver;

    private String description;

    private Boolean completed;

    private int reward;

    private Date createdAt;

    private Date updatedAt;

}
