package com.wipro.pringBoot.controller;

import com.wipro.pringBoot.model.Employee;
import com.wipro.pringBoot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @RequestMapping(value = "/employees")
    private List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }
    // find employee by id or get method
    @GetMapping(value = "/employees/{id}")
    private Employee getEmployeeById(@PathVariable long id){
        return employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
    }

    // post method
    @PostMapping(value = "/employees")
    private Employee addEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    //Put method / update method
    @PutMapping(value = "/employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable long id,@RequestBody Employee employee){

        try{
            Employee existEmployee = getEmployeeById(id);

//            if (updatedEmployee.getId() > 0) {
//                existEmployee.setId(updatedEmployee.getId());
//            }
//            if (updatedEmployee.getEmail() != null) {
//                existingEmployee.setEmail(updatedEmployee.getEmail());
//            }
//            if (updatedEmployee.getFirstName() != null) {
//                existingEmployee.setFirstName(updatedEmployee.getFirstName());
//            }
//            if (updatedEmployee.getLastName() != null) {
//                existingEmployee.setLastName(updatedEmployee.getLastName());
//            }


            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<Employee>(employee,HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping(value = "/employees/{id}")
    private void deleteEmployee(@PathVariable Long id){
        employeeRepository.deleteById(id);
    }

}
