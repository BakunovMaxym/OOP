package com.lb8.rest_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lb8.rest_api.persistence.models.Day;
import com.lb8.rest_api.service.DayService;

@RestController
public class DayController {

    private final DayService dayService;

    @Autowired
    public DayController(DayService dayService){
        this.dayService = dayService;
    }


    @PostMapping("/days")
    public ResponseEntity<String> create(@RequestBody Day day) {
        dayService.create(day);
        System.out.println(day);
        return new ResponseEntity<>("Successfuly added", HttpStatus.CREATED);
    }


    @GetMapping(value = "/days")
    public ResponseEntity<List<Day>> read() {
       final List<Day> days = dayService.readAll();
       System.out.println(days);
       return days != null &&  !days.isEmpty()
       ? new ResponseEntity<>(days, HttpStatus.OK)
       : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    
    @GetMapping(value = "/days/{id}")
    public ResponseEntity<Day> read(@PathVariable(name = "id") int id) {
        final Day day = dayService.read(id);

       return day != null
               ? new ResponseEntity<>(day, HttpStatus.OK)
               : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping(value = "/days/{id}")
    public ResponseEntity<String> update(@PathVariable(name = "id") int id, @RequestBody Day day) {
        final boolean updated = dayService.update(day, id);
    
        return updated
                ? new ResponseEntity<>("Successfuly edited", HttpStatus.OK)
                : new ResponseEntity<>("Error trying to edit day", HttpStatus.NOT_MODIFIED);
    }


    @DeleteMapping(value = "/days/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") int id) {
       final boolean deleted = dayService.delete(id);

       return deleted
               ? new ResponseEntity<>("Successfuly deleted", HttpStatus.OK)
               : new ResponseEntity<>("Error trying to delete day", HttpStatus.NOT_MODIFIED);
    }
}
