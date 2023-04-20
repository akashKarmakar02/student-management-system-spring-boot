package com.akash.studentmanagementsystem.service;

import com.akash.studentmanagementsystem.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    void saveStudent(Student student);

    Student getStudentById(Long id);
    void updateStudent(Student student);
    void deleteStudentById(Long id);
}
