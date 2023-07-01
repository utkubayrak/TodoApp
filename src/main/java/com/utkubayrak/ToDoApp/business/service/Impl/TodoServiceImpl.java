package com.utkubayrak.ToDoApp.business.service.Impl;

import com.utkubayrak.ToDoApp.bean.ModelMapperBean;
import com.utkubayrak.ToDoApp.business.dto.TodoDto;
import com.utkubayrak.ToDoApp.business.service.ITodoGenericsService;
import com.utkubayrak.ToDoApp.data.entity.TodoEntity;
import com.utkubayrak.ToDoApp.data.repository.ITodoRepository;
import com.utkubayrak.ToDoApp.exception.BadRequestException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Log4j2
@Service
public class TodoServiceImpl implements ITodoGenericsService<TodoDto, TodoEntity> {
    //Injection
    private final ModelMapperBean modelMapperBean;

    private final ITodoRepository iTodoRepository;

    //Model Mapper
    @Override
    public TodoDto EntityToDto(TodoEntity todoEntity) {
        return modelMapperBean.modelMapperMethod().map(todoEntity, TodoDto.class);
    }

    @Override
    public TodoEntity DtoToEntity(TodoDto todoDto) {
        return modelMapperBean.modelMapperMethod().map(todoDto, TodoEntity.class);
    }

    /////CRUD/////
    //Create
    @Transactional
    @Override
    public TodoDto todoServiceCreate(TodoDto todoDto) {
        if (todoDto != null) {
            TodoEntity todoEntity = iTodoRepository.save(DtoToEntity(todoDto));
            todoDto.setId(todoEntity.getId());
            todoDto.setSystemDate(todoDto.getSystemDate());
        } else {
            throw new BadRequestException("Todo Eklenemedi");
        }
        return todoDto;
    }

    @Override
    public List<TodoDto> todoServiceList() {
        Iterable<TodoEntity> blogEntityIterable = iTodoRepository.findAll();
        List<TodoDto> list = new ArrayList<>();
        for (TodoEntity entity : blogEntityIterable) {
            TodoDto blogDto = EntityToDto(entity);
            list.add(blogDto);
        }
        return list;
    }

    @Override
    public TodoDto todoServiceFindById(Long id) {
        TodoEntity todoEntity = null;
        if (id != null) {
            todoEntity = iTodoRepository.findById(id)
                    .orElseThrow(() -> new ResourceAccessException(id + " nolu ID bulunamadı"));
        }
        return EntityToDto(todoEntity);
    }

    @Transactional
    @Override
    public TodoDto todoServiceDeleteById(Long id) {
        TodoDto todoDtoDeleteFind = todoServiceFindById(id);
        TodoEntity todoEntity = DtoToEntity(todoDtoDeleteFind);
        iTodoRepository.delete(todoEntity);
        return todoDtoDeleteFind;
    }

    @Transactional
    @Override
    public TodoDto todoServiceUpdateById(Long id, TodoDto todoDto) {
        TodoEntity todoEntity = DtoToEntity(todoServiceFindById(id));
        if (todoEntity != null) {
            todoEntity.setId(id);
            todoEntity.setContent(todoDto.getContent());
            iTodoRepository.save(todoEntity);
            todoDto.setId(todoEntity.getId());
            todoDto.setSystemDate(todoDto.getSystemDate());
        }
        return EntityToDto(todoEntity);
    }

    @Transactional
    @Override
    public TodoDto todoServiceCheckedById(Long id) {
        TodoEntity todoEntity = DtoToEntity(todoServiceFindById(id));
        boolean newCheckStatus = !todoEntity.getIsChecked();
        todoEntity.setIsChecked(newCheckStatus);
        iTodoRepository.save(todoEntity);
        return EntityToDto(todoEntity);
    }
    @Transactional
    @Override
    public String allDeleteService() {
        iTodoRepository.deleteAll();
        log.info("Todolist silindi");
        return "Tüm todolar silindi";
    }

    @Transactional
    @Override
    public String allCheckedService() {
        Iterable<TodoEntity> todoEntities = iTodoRepository.findAll();
        boolean hasChecked = false;
        for (TodoEntity todoEntity : todoEntities) {
            if (!todoEntity.getIsChecked()) {
                hasChecked = true;
                todoEntity.setIsChecked(true);
            }
        }
        if (hasChecked) {
            return "Tüm todolar check edildi.";
        }else{
            return "Tüm todolar zaten check edildi";
        }
    }
    @Transactional
    @Override
    public String allDoneDeleteService() {
        Iterable<TodoEntity> todoEntities = iTodoRepository.findAll();
        boolean hasChecked = false;
        for (TodoEntity todoEntity : todoEntities) {
            if (todoEntity.getIsChecked()) {
                hasChecked = true;
                iTodoRepository.deleteById(todoEntity.getId());
            }
        }
        if (hasChecked) {
            return "Tüm check edilen todolar silindi";
        } else {
            return "Check edilmiş todo yoktur.";
        }
    }


    @Override
    public List<TodoDto> doneListService() {
        List<TodoEntity> checkedTodos = iTodoRepository.findByIsChecked(true);
        List<TodoDto> list = new ArrayList<>();
        for (TodoEntity entity : checkedTodos) {
            TodoDto blogDto = EntityToDto(entity);
            list.add(blogDto);
        }
        return list;
    }

    @Override
    public List<TodoDto> uncompletedListService() {
        List<TodoEntity> checkedTodos = iTodoRepository.findByIsChecked(false);
        List<TodoDto> list = new ArrayList<>();
        for (TodoEntity entity : checkedTodos) {
            TodoDto blogDto = EntityToDto(entity);
            list.add(blogDto);
        }
        return list;
    }
}
