package com.pramodvaddiraju.taskflow.service;

import jakarta.persistence.*;

@Entity
@Table(name = "taskflow_table")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
