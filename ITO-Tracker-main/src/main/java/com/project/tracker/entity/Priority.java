package com.project.tracker.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Getter
@Setter
public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int priorityId;
    private String priorityName;
}
