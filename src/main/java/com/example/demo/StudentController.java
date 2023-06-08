package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("students")
@SessionAttributes("students/selectedDepartment")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute(new Student());
		return "form.html";
	}

	@PostMapping("add")
	public String add(
		@ModelAttribute("student") @Valid Student student,
		BindingResult bindingResult,
		Model model,
		@ModelAttribute("department") String select
	) {
		if(bindingResult.hasErrors()) {
			return "form/html";
		}
		if(studentService.get(student.getId()) == null) {
			bindingResult.rejectValue("id", "duplicate id");
		}
		
		studentService.save(student);
		return "form.html";
	}
	
	@PostMapping("/reset")
    public String reset(Model model) {
        model.addAttribute("student", new Student());
        return "form.html";
    }
	@ModelAttribute("selectedDepartment")
    public String getSelectedDepartment(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String selectedDepartment = (String) session.getAttribute("selectedDepartment");
        if (selectedDepartment == null) {
            selectedDepartment = "Department A";
        }
        return selectedDepartment;
    }

    @PostMapping("/department")
    public String selectDepartment(@RequestParam("department") String selectedDepartment,
                                    HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("selectedDepartment", selectedDepartment);
        return "redirect:/";
    }
}

