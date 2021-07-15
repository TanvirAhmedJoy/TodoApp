package com.todolist.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class todoService {
    @Autowired
    private todoRepository repo;

    public List<todo> listAll() {
        return(List<todo>) repo.findAll();
    }


    public void save(todo td) {

        repo.save(td);
    }

    public todo get(Integer id) throws taskNotFoundException {
        Optional<todo> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new taskNotFoundException("could not find the task id"+ id);
    }
    public void delete(Integer id) throws taskNotFoundException {
        Long count=repo.countById(id);
        if(count==null || count==0){
            throw new taskNotFoundException("could not find the task id"+ id);

        }
        repo.deleteById(id);
    }
}
