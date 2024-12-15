package com.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.dto.EmpRequestDTO;
import com.emp.dto.EmpResponseDTO;

import com.emp.exception.EmployeeNotFoundException;
import com.emp.repository.EmployeeRepository;
import com.emp.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/emp")
public class EmpController {
	
	@Autowired
    private EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public EmpResponseDTO addEmp(@RequestBody @Valid EmpRequestDTO empRequestDTO) {
        return employeeService.saveEmployee(empRequestDTO);
    }
    @GetMapping("/empList")
    public List<EmpResponseDTO> findAllEmployees() {
        return employeeService.getEmpList();
    }
    
   @GetMapping("/{id}")
    public ResponseEntity<EmpResponseDTO> getUser(@PathVariable int id) throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    

}
