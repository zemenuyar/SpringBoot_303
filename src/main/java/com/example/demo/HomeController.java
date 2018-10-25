package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.Callable;

@Controller
public class HomeController {
    @Autowired
    CourseRespository CourseRespository;
    @RequestMapping("/")
    public String listCourses(Model model){
        model.addAttribute("courses", CourseRespository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String processForm(Model model){
        model.addAttribute("course",new Course());
        return "courseform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Course course, BindingResult result){
    if (result.hasErrors()){
        return "courseform";
    }
    CourseRespository.save(course);
    return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("course",CourseRespository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id")long id, Model model){
        model.addAttribute("course",CourseRespository.findById(id).get());
        return "courseform";

    }
    @RequestMapping("/delete/{id}")
    public String delcourse(@PathVariable("id")long id, Model model){
        CourseRespository.deleteById(id);
        return "redirect:/";


            }
        }


