package org.depromeet.fill_day.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.depromeet.fill_day.domain.dto.TodoDTO;
import org.depromeet.fill_day.domain.dto.TodoDTO;
import org.depromeet.fill_day.domain.entity.Todo;
import org.depromeet.fill_day.exception.NotFoundException;
import org.depromeet.fill_day.repository.TodoRepository;
import org.depromeet.fill_day.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TodoService {

    private TodoRepository todoRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public TodoService(TodoRepository todoRepository,
                       ObjectMapper objectMapper) {
        this.todoRepository = todoRepository;
        this.objectMapper = objectMapper;
    }

    public TodoDTO create(TodoDTO newTodoDTO) {
        Todo newTodo = objectMapper.convertValue(newTodoDTO, Todo.class);
        Todo createdTodo = todoRepository.save(newTodo);

        return objectMapper.convertValue(createdTodo, TodoDTO.class);
    }

    public Optional<TodoDTO> findByUID(String uid) {
        Optional<Todo> foundTodo = todoRepository.findById(UUID.fromString(uid));

        if (foundTodo.isPresent()) {
            TodoDTO foundDTO = objectMapper.convertValue(foundTodo.get(), TodoDTO.class);
            return Optional.of(foundDTO);
        }
        return Optional.empty();
    }

    public TodoDTO update(String uid, TodoDTO updatedTodoDTO) {
        Optional<TodoDTO> foundTodo = findByUID(uid);
        foundTodo.orElseThrow(NotFoundException::new);

        Todo updatedTodo = objectMapper.convertValue(updatedTodoDTO, Todo.class);
        updatedTodo.setUid(UUID.fromString(uid));
        Todo modifiedTodo = todoRepository.save(updatedTodo);

        return objectMapper.convertValue(modifiedTodo, TodoDTO.class);
    }

    public void delete(String uid) {
        Optional<TodoDTO> foundTodo = findByUID(uid);
        foundTodo.orElseThrow(NotFoundException::new);

        todoRepository.deleteById(UUID.fromString(uid));
    }
}
