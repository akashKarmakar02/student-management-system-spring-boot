package com.akash.studentmanagementsystem.controller;

import com.akash.studentmanagementsystem.entity.Student;
import com.akash.studentmanagementsystem.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // handle routing to '/'
    @GetMapping("/")
    public String homeMapping() {
        return "redirect:/students";
    }

    // handle method to list students and return model and view
    @GetMapping("/students")
    public String listStudent(Model model) {
        model.addAttribute("student", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("students/new")
    public String createStudentForm(Model model) {
        Student student = new Student();

        model.addAttribute("student", student);
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute Student student,
                                Model model) {
        //get student from database by id
        Student existingStudent = studentService.getStudentById(id);

        // updating student object
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setCourse(student.getCourse());
        studentService.updateStudent(existingStudent);

        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }
}