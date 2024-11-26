package com.lb8.rest_api.persistence.models;

import java.time.LocalTime;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)

public class Lesson {

    private int id;
    private String name;
    private String teacher;
    private LocalTime time;
}
