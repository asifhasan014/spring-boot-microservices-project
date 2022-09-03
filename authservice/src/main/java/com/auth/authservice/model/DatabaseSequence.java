package com.auth.authservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "DbSequence")
public class DatabaseSequence {

    @Id
    private String id;

    private long seq;

    //getters and setters omitted
}