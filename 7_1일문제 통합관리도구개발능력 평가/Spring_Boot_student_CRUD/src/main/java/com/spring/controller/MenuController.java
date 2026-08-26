package com.spring.controller;

import com.spring.dto.StudentDTO;
import com.spring.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/menus")
public class MenuController {
    private final StudentService studentService;

    public MenuController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ModelAndView list(ModelAndView view,
                             @RequestParam(name = "category", required = false) String category,
                             @RequestParam(name = "keyword", required = false) String keyword) {
        List<StudentDTO> list;
        if ((category != null && !category.isEmpty())
                && (keyword != null && !keyword.isEmpty())) {
            list = studentService.search(keyword, category);
        } else {
            list = studentService.findAll();
        }

        view.setViewName("menu/list");
        view.addObject("menus", list);
        view.addObject("category", category);
        view.addObject("keyword", keyword);

        return view;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("student", new StudentDTO());
        model.addAttribute("departments", studentService.findAllDepartments());
        return "menu/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("student") StudentDTO student,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", studentService.findAllDepartments());
            return "menu/register";
        }

        studentService.insert(student);
        return "redirect:/menus";
    }

    @GetMapping("/edit/{studentId}")
    public String editForm(@PathVariable int studentId, Model model) {
        StudentDTO student = studentService.findById(studentId);
        if (student == null) {
            return "redirect:/menus";
        }
        model.addAttribute("student", student);
        model.addAttribute("departments", studentService.findAllDepartments());
        return "menu/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("student") StudentDTO student,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", studentService.findAllDepartments());
            return "menu/edit";
        }

        studentService.update(student);
        return "redirect:/menus";
    }

    @PostMapping("/delete/{studentId}")
    public String delete(@PathVariable int studentId) {
        studentService.delete(studentId);
        return "redirect:/menus";
    }
}
