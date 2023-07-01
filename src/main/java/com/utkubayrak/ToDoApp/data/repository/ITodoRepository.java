package com.utkubayrak.ToDoApp.data.repository;

import com.utkubayrak.ToDoApp.data.entity.TodoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ITodoRepository extends CrudRepository<TodoEntity, Long> {

    List<TodoEntity> findByIsChecked(boolean isChecked);
}
