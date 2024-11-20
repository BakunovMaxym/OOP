package com.lb8.rest_api.persistence.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)

public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;

}
