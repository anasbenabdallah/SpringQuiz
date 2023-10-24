package com.spingbootquiz.azizpart.service;


import com.spingbootquiz.azizpart.Dto.EmployeeDTO;
import com.spingbootquiz.azizpart.Dto.LoginDTO;
import com.spingbootquiz.azizpart.models.Employee;
import com.spingbootquiz.azizpart.models.Quiz;
import com.spingbootquiz.azizpart.response.LoginResponse;

import java.util.List;

public interface EmployeeService {
    String addEmployee(EmployeeDTO employeeDTO);
    LoginResponse loginEmployee(LoginDTO loginDTO);
    void assignQuizToEmployee(String email, Long quizId);
    List<Quiz> getAssignedQuizzes(String email);
    Employee getEmployeeById(Long id);




}
