package com.wipro.pringBoot.controller;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(long id){
        super("Could not found employee for this id  : "+id);
    }
}
