package com.spingbootcours.Tahapart.Dto;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Pattern;


public class EmployeeDTO {
    private int employeeid;
    private String employeename;
    private String email;
    private String password;
    @NotNull(message = "Role is required.")
    @Pattern(regexp = "^(user|professor)$", message = "Role can be either 'user' or 'professor'.")
    private String role;

    public EmployeeDTO(int employeeid, String employeename, String email, String password, String role) {
        this.employeeid = employeeid;
        this.employeename = employeename;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public EmployeeDTO() {
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public String getEmployeename() {
        return employeename;
    }
    public String getRole() {
        return role;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employeeid=" + employeeid +
                ", employeename='" + employeename + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
