package com.utkubayrak.ToDoApp.business.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITodoGenericsService<D,E>{
    // ### Model Mapper ###############################
    public D EntityToDto(E e);

    public E DtoToEntity(D d);

    // ### CRUD ###############################
    // CREATE
    public D todoServiceCreate(D d);

    // LIST
    public List<D> todoServiceList();

    // FIND
    public D todoServiceFindById(Long id);

    // DELETE
    public D todoServiceDeleteById(Long id);

    // UPDATE
    public D todoServiceUpdateById(Long id, D d);

    // CHECKED
    public D todoServiceCheckedById(Long id);

    //ALL DELETE
    public String allDeleteService();

    //ALL CHECKED
    public String allCheckedService();

    //All DONE CHECKED
    public String allDoneDeleteService();

    //JUST CHECKED LİST
    public List<D> doneListService();
    //UNCOMPLETED  LİST
    public List<D> uncompletedListService();

}


