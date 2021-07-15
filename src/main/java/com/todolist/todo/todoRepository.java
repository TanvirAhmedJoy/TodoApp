package com.todolist.todo;

import org.springframework.data.repository.CrudRepository;

public interface todoRepository extends CrudRepository<todo, Integer> {
    public Long countById(Integer id);

}
