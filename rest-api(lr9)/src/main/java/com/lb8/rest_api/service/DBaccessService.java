package com.lb8.rest_api.service;

import java.util.List;

public interface DBaccessService <T> {

    void create(T entity);

    List<T> readAll();

    T read(int id);

    boolean update(T entity, int id);

    boolean delete(int id);

    
} 
