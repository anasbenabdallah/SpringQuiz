package com.spingbootquiz.azizpart.response;

import com.spingbootquiz.azizpart.Dto.EmployeeDTO;

public class LoginResponse {
    String message;
    Boolean status;

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public LoginResponse(EmployeeDTO employee) {
        this.employee = employee;
    }

    private EmployeeDTO employee;
    public LoginResponse(String message, boolean status, EmployeeDTO employee) {
        this.message = message;
        this.status = status;
        this.employee = employee;
    }

    public LoginResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
