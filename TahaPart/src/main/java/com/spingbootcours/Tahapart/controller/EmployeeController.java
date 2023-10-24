package com.spingbootcours.Tahapart.controller;

import com.spingbootcours.Tahapart.Dto.EmployeeDTO;
import com.spingbootcours.Tahapart.Dto.LoginDTO;
import com.spingbootcours.Tahapart.models.Employee;
import com.spingbootcours.Tahapart.models.Quiz;
import com.spingbootcours.Tahapart.repository.EmployeeRepo;
import com.spingbootcours.Tahapart.repository.QuizRepository;
import com.spingbootcours.Tahapart.response.LoginResponse;
import com.spingbootcours.Tahapart.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/employee")
@Validated
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private EmployeeRepo employeeRepo;

    @PostMapping(path = "/save")
    public ResponseEntity<?> saveEmployee( @RequestBody @Valid EmployeeDTO employeeDTO) {
        System.out.println(employeeDTO);
        String id = employeeService.addEmployee(employeeDTO);
        return ResponseEntity.ok(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.append(errorMessage).append("\n");
        });

        return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
    }
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO)
    {
        LoginResponse loginResponse = employeeService.loginEmployee(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }
    @PostMapping("/assignQuiz/{quizId}")
    public ResponseEntity<?> assignQuizToEmployee(@PathVariable Long quizId, @RequestBody List<String> assignedTo) {
        Quiz quiz = quizRepository.findById(quizId).orElse(null); // Check if quiz exists

        if (quiz == null) {
            return ResponseEntity.badRequest().body("Quiz not found!"); // Return a response for quiz not found
        }

        try {
            // Assign the quiz to each specified user
            for (String email : assignedTo) {
                Employee employee = employeeRepo.findByEmail(email);
                if (employee != null) {
                    // Add the quiz to the employee's assigned quizzes
                    employee.getQuizzes().add(quiz);
                    // Add the email to the quiz's assignedTo list
                    quiz.getAssignedTo().add(email);
                    employeeRepo.save(employee);
                }
            }

            return ResponseEntity.ok("Quiz assigned successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/{email}/quizzes")
    public ResponseEntity<List<Quiz>> getAssignedQuizzes(@PathVariable String email) {
        List<Quiz> quizzes = employeeService.getAssignedQuizzes(email);
        return ResponseEntity.ok(quizzes);
    }
}
