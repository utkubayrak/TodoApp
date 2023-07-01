package com.utkubayrak.ToDoApp.controller.api.impl;

import com.utkubayrak.ToDoApp.business.dto.TodoDto;
import com.utkubayrak.ToDoApp.business.service.ITodoGenericsService;
import com.utkubayrak.ToDoApp.controller.ITodoGenericsApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor


@RestController
@CrossOrigin
@RequestMapping("/todo/api")
public class TodoApiImpl implements ITodoGenericsApi<TodoDto> {

    private final ITodoGenericsService iTodoGenericsService;

    @Override
    @GetMapping({"/", "/index"})
    public ResponseEntity<String> getRoot() {
        return null;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<?> todoServiceCreate(@Valid @RequestBody TodoDto todoDto) {
        return ResponseEntity.ok(iTodoGenericsService.todoServiceCreate(todoDto));
    }

    @Override
    @GetMapping(value = "/list")
    public ResponseEntity<List<TodoDto>> todoServiceList() {
        return ResponseEntity.ok(iTodoGenericsService.todoServiceList());
    }

    @Override
    @GetMapping({"/find", "/find/{id}"})
    public ResponseEntity<?> todoServiceFindById(@PathVariable(name = "id", required = false) Long id) {
        return ResponseEntity.ok(iTodoGenericsService.todoServiceFindById(id));
    }

    @Override
    @DeleteMapping({"/delete", "/delete/{id}"})
    public ResponseEntity<?> todoServiceDeleteById(@PathVariable(name = "id", required = false) Long id) {
        return ResponseEntity.ok(iTodoGenericsService.todoServiceDeleteById(id));
    }

    @Override
    @PutMapping({"/update", "/update/{id}"})
    public ResponseEntity<?> todoServiceUpdateById(@PathVariable(name = "id", required = false) Long id,@Valid @RequestBody  TodoDto todoDto) {
        return ResponseEntity.ok(iTodoGenericsService.todoServiceUpdateById(id,todoDto));
    }

    @Override
    @PutMapping({"/checked", "/checked/{id}"})
    public ResponseEntity<?> todoServiceCheckById(@PathVariable(name = "id", required = false) Long id){
        return ResponseEntity.ok(iTodoGenericsService.todoServiceCheckedById(id));
    }

    @Override
    @DeleteMapping({"/all/delete"})
    public ResponseEntity<String> allDeleteService() {
        return ResponseEntity.ok(iTodoGenericsService.allDeleteService());
    }

    @Override
    @PutMapping({"/all/checked"})
    public ResponseEntity<String> allCheckedService() {
        return ResponseEntity.ok(iTodoGenericsService.allCheckedService());
    }

    @Override
    @DeleteMapping({"/all/done/delete"})
    public ResponseEntity<String> allDoneDeleteService() {
        return ResponseEntity.ok(iTodoGenericsService.allDoneDeleteService());
    }

    @Override
    @GetMapping({"/all/done/list"})
    public ResponseEntity<List<TodoDto>> doneListService() {
        return ResponseEntity.ok(iTodoGenericsService.doneListService());
    }

    @Override
    @GetMapping({"all/uncompleted/list"})
    public ResponseEntity<List<TodoDto>> uncompletedListService() {
        return ResponseEntity.ok(iTodoGenericsService.uncompletedListService());
    }
}
