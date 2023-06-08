package com.example.demo;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("students")
public class StudentController {
  @Autowired
  private StudentService studentService;

  @GetMapping("add")
  public String add(Model model) {

    model.addAttribute("student", new Student());
    model.addAttribute("departments", getDepartments());

    return "form";
  }

  @PostMapping("add")
  public String add(
        @ModelAttribute("student") @Valid Student student,
        BindingResult bindingResult,
        Model model,
        HttpSession session
  ) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("departments", getDepartments());
      return "form";
    }

    if (studentService.get(student.getId()) != null) {
      bindingResult.rejectValue("id", "error.student", "Student ID already exists");
      model.addAttribute("departments", getDepartments());
      return "form";
    }

    if (studentService.get(student.getId()) != null) {
      bindingResult.rejectValue("id", "duplicate id", "duplicate id");
    }

    System.out.println(student);
    studentService.save(student);

    model.addAttribute("departments", getDepartments());
    session.setAttribute("selectedDepartment", student.getDepartment());

    return "redirect:/students/add";
  }

  private List<String> getDepartments() {
    return Arrays.asList("Department 1", "Department 2", "Department 3");
  }
}

