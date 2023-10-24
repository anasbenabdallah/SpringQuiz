package com.spingbootquiz.azizpart.service.impl;

import com.spingbootquiz.azizpart.Dto.EmployeeDTO;
import com.spingbootquiz.azizpart.Dto.LoginDTO;
import com.spingbootquiz.azizpart.models.Employee;

import com.spingbootquiz.azizpart.models.Quiz;
import com.spingbootquiz.azizpart.repository.EmployeeRepo;
import com.spingbootquiz.azizpart.repository.QuizRepository;
import com.spingbootquiz.azizpart.response.LoginResponse;
import com.spingbootquiz.azizpart.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class EmployeeIMPL  implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepo.existsByEmail(employeeDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        Employee employee = new Employee(
                employeeDTO.getEmployeeid(),
                employeeDTO.getEmployeename(),
                employeeDTO.getEmail(),
                this.passwordEncoder.encode(employeeDTO.getPassword()),
                employeeDTO.getRole()
        );
        employee.setRole(employeeDTO.getRole());
        employeeRepo.save(employee);
        return employee.getEmployeename();
    }
    EmployeeDTO employeeDTO;
    @Override
    public LoginResponse loginEmployee(LoginDTO loginDTO) {
        Employee employee1 = employeeRepo.findByEmail(loginDTO.getEmail());
        if (employee1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = employee1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<Employee> employee = employeeRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    EmployeeDTO employeeData = new EmployeeDTO(
                            employee1.getEmployeeid(),
                            employee1.getEmployeename(),
                            employee1.getEmail(),
                            null,  // Do not send back the password
                            employee1.getRole()
                    );
                    return new LoginResponse("Login Success", true, employeeData);  // Send back the user data
                } else {
                    return new LoginResponse("Login Failed", false, null);
                }
            } else {
                return new LoginResponse("Password Not Match", false, null);
            }
        }else {
            return new LoginResponse("Email not exists", false, null);
        }
    }
    @Override
    public void assignQuizToEmployee(String email, Long quizId) {
        Employee employee = employeeRepo.findByEmail(email);
        if (employee == null) {
            throw new RuntimeException("Employee not found with email: " + email);
        }

        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found!"));

        // Assuming you have a relationship between Employee and Quiz in your models
        employee.getQuizzes().add(quiz);

        employeeRepo.save(employee);
    }
    @Override
    public List<Quiz> getAssignedQuizzes(String email) {
        Employee employee = employeeRepo.findByEmail(email);
        if (employee == null) {
            throw new RuntimeException("Employee not found with email: " + email);
        }
        return new ArrayList<>(employee.getQuizzes());  // Return the quizzes assigned to the employee
    }
    @Override
    public Employee getEmployeeById(Long id) {
        // Implement the logic to fetch an employee by ID from your data source
        // Example:
        return employeeRepo.findByEmployeeid(id.intValue()).orElse(null);
    }



}
