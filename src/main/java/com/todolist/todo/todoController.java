package com.todolist.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class todoController {
    @Autowired
    private todoService service;
    @GetMapping("/todos")
    public String showUserList(Model model){
        List<todo> listTodo=service.listAll();
        model.addAttribute("listTodo",listTodo);
        return "todos";

    }

    @GetMapping("/todos/new")
    public String showNewForm(Model model){
        model.addAttribute("task", new todo());
        model.addAttribute("pageTitle","Add new Task");
        return "task_form";
    }
    @PostMapping("/todos/save")
    public String saveTodo(todo td, RedirectAttributes ra){
        service.save(td);
        ra.addFlashAttribute("message","the user has been saved sucessfully");
        return "redirect:/todos";
    }

    @GetMapping("/todos/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id,Model model,RedirectAttributes ra){
        try {
            todo tod= service.get(id);

            model.addAttribute("tod",tod);
            model.addAttribute("pageTitle","edit attribute" + id);
            return "task_form";


        } catch (taskNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/todos";
        }
    }

    @GetMapping("/todos/delete/{id}")
    public String deleteTask(@PathVariable("id") Integer id,RedirectAttributes ra){
        try {
            service.delete(id);



        } catch (taskNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());

        }
        return "redirect:/todos";

    }


}
