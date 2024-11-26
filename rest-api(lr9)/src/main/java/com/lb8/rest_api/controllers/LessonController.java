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

import com.lb8.rest_api.persistence.models.Lesson;
import com.lb8.rest_api.service.LessonService;

@RestController
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService){
        this.lessonService = lessonService;
    }


    @PostMapping("/lessons")
    public ResponseEntity<String> create(@RequestBody Lesson lesson) {
        lessonService.create(lesson);
        System.out.println(lesson);
        return new ResponseEntity<>("Successfuly added", HttpStatus.CREATED);
    }


    @GetMapping(value = "/lessons")
    public ResponseEntity<List<Lesson>> read() {
       final List<Lesson> lessons = lessonService.readAll();
       System.out.println(lessons);
       return lessons != null &&  !lessons.isEmpty()
       ? new ResponseEntity<>(lessons, HttpStatus.OK)
       : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    
    @GetMapping(value = "/lessons/{id}")
    public ResponseEntity<Lesson> read(@PathVariable(name = "id") int id) {
        final Lesson lesson = lessonService.read(id);

       return lesson != null
               ? new ResponseEntity<>(lesson, HttpStatus.OK)
               : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping(value = "/lessons/{id}")
    public ResponseEntity<String> update(@PathVariable(name = "id") int id, @RequestBody Lesson lesson) {
        final boolean updated = lessonService.update(lesson, id);
    
        return updated
                ? new ResponseEntity<>("Successfuly edited", HttpStatus.OK)
                : new ResponseEntity<>("Error trying to edit lesson", HttpStatus.NOT_MODIFIED);
    }


    @DeleteMapping(value = "/lessons/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") int id) {
       final boolean deleted = lessonService.delete(id);

       return deleted
               ? new ResponseEntity<>("Successfuly deleted", HttpStatus.OK)
               : new ResponseEntity<>("Error trying to delete lesson", HttpStatus.NOT_MODIFIED);
    }
}
