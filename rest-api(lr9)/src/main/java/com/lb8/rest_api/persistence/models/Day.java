package com.lb8.rest_api.persistence.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)

public class Day {
    private int id;
    private String name;
    private String firstSubj; 
    private String secondSubj;
    private String thirdSubj; 
    private String fourthSubj;
}
