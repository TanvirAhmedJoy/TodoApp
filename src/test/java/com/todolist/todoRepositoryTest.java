package com.todolist;

import com.todolist.todo.todo;
import com.todolist.todo.todoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class todoRepositoryTest {
    @Autowired
    private todoRepository repo;

    @Test
    public void testAddNew(){
        todo ts= new todo();
        ts.setTaskName("spring boot tutorial");
        ts.setTaskDescription("amigos code and java techy preferable");
        todo saveTask=repo.save(ts);

        Assertions.assertThat(saveTask).isNotNull();
        Assertions.assertThat(saveTask.getId()).isGreaterThan(0);


    }
    @Test
    public void testListAll(){
        Iterable<todo> todos = repo.findAll();
        Assertions.assertThat(todos).hasSizeGreaterThan(0);
        for(todo task:todos){
            System.out.println(task);
        }
    }

    @Test
    public void testUpdate(){
        Integer taskID=2;
        Optional<todo> optionalUser = repo.findById(taskID);
        todo task= optionalUser.get();
        task.setTaskDescription("amigos code and java techy preferable");
        repo.save(task);

        todo updateTodo=repo.findById(taskID).get();
        Assertions.assertThat(updateTodo.getTaskDescription()).isEqualTo("amigos code and java techy preferable");


    }

    @Test
    public void testGate(){
        Integer taskID=2;
        Optional<todo> optionalUser = repo.findById(taskID);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());


    }

    @Test
    public void testDelete(){
        Integer taskID=3;
        repo.deleteById(taskID);
        Optional<todo> optionalUser = repo.findById(taskID);
        Assertions.assertThat(optionalUser).isNotPresent();

    }




}
