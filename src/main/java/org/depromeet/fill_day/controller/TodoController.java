package org.depromeet.fill_day.controller;

import org.depromeet.fill_day.domain.dto.TodoDTO;
import org.depromeet.fill_day.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/todos")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<TodoDTO> create(@RequestBody TodoDTO newTodo) {
        return ResponseEntity.ok(todoService.create(newTodo));
    }

    @GetMapping(value = "/{uid}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<TodoDTO> findByUID(@PathVariable String uid) {
        return todoService.findByUID(uid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{uid}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<TodoDTO> update(@PathVariable String uid, @RequestBody TodoDTO updatedTodo) {
        return ResponseEntity.ok(todoService.update(uid, updatedTodo));
    }

    @DeleteMapping(value = "/{uid}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity delete(@PathVariable String uid) {
        todoService.delete(uid);
        return ResponseEntity.ok().build();
    }

}
