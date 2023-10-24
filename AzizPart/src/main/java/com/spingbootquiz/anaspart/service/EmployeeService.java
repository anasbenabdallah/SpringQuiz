package com.spingbootquiz.anaspart.service;


import com.spingbootquiz.anaspart.Dto.EmployeeDTO;
import com.spingbootquiz.anaspart.Dto.LoginDTO;
import com.spingbootquiz.anaspart.models.Employee;
import com.spingbootquiz.anaspart.models.Quiz;
import com.spingbootquiz.anaspart.payload.response.LoginMesage;
import com.spingbootquiz.anaspart.response.LoginResponse;

import java.util.List;

public interface EmployeeService {
    String addEmployee(EmployeeDTO employeeDTO);
    LoginResponse loginEmployee(LoginDTO loginDTO);
    void assignQuizToEmployee(String email, Long quizId);
    List<Quiz> getAssignedQuizzes(String email);
    Employee getEmployeeById(Long id);




}
