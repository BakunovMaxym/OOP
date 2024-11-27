package com.lb8.rest_api.service;

import java.util.List;

public abstract class DBaccessService <T> {

    public abstract void create(T entity);

    public abstract List<T> readAll();

    public abstract T read(int id);

    public abstract boolean update(T entity, int id);

    public abstract boolean delete(int id);

    
} 
