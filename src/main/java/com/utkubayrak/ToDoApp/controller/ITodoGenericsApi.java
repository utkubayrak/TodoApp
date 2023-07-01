package com.utkubayrak.ToDoApp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITodoGenericsApi<D> {
    // Spring MVC (Thymeleaf)
    public ResponseEntity<String> getRoot();

    // ### CRUD ###############################
    // CREATE
    public ResponseEntity<?>  todoServiceCreate(D d);

    // LIST
    public ResponseEntity<List<D>>  todoServiceList();

    // FIND
    public ResponseEntity<?>  todoServiceFindById(Long id);

    // DELETE
    public  ResponseEntity<?> todoServiceDeleteById(Long id);

    // UPDATE
    public ResponseEntity<?>  todoServiceUpdateById(Long id, D d);

    // CHECKED
    public ResponseEntity<?>  todoServiceCheckById(Long id);

    //All Delete
    public ResponseEntity<String>  allDeleteService();

    //All Checked
    public ResponseEntity<String>  allCheckedService();

    //All Done Checked
    public ResponseEntity<String>  allDoneDeleteService();
    //All Done List
    public ResponseEntity<List<D>>  doneListService();
    //Uncompleted  List
    public ResponseEntity<List<D>>  uncompletedListService();


}

