package com.spingbootcours.Tahapart.service;


import com.spingbootcours.Tahapart.Dto.EmployeeDTO;
import com.spingbootcours.Tahapart.Dto.LoginDTO;
import com.spingbootcours.Tahapart.response.LoginResponse;
import com.spingbootcours.Tahapart.models.Employee;
import com.spingbootcours.Tahapart.models.Quiz;

import java.util.List;

public interface EmployeeService {
    String addEmployee(EmployeeDTO employeeDTO);
    LoginResponse loginEmployee(LoginDTO loginDTO);
    void assignQuizToEmployee(String email, Long quizId);
    List<Quiz> getAssignedQuizzes(String email);
    Employee getEmployeeById(Long id);




}
