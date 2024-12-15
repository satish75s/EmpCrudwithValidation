package com.emp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.dto.EmpRequestDTO;
import com.emp.dto.EmpResponseDTO;
import com.emp.entity.Employee;
import com.emp.exception.EmployeeNotFoundException;
import com.emp.repository.EmployeeRepository;


@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ModelMapper modelMapper;

	public EmpResponseDTO saveEmployee(EmpRequestDTO empRequestDTO) {

		// dto to entity conversion
		
		TypeMap<EmpRequestDTO, Employee> propertyMapper = modelMapper.typeMap(EmpRequestDTO.class, Employee.class);
		propertyMapper.addMapping(EmpRequestDTO::getEmpName, Employee::setEName);
		propertyMapper.addMapping(EmpRequestDTO::getContactNumber, Employee::setContactNum);

		Employee employee = employeeRepository.save(modelMapper.map(empRequestDTO, Employee.class));

		// entity to dto conversion

		TypeMap<Employee, EmpResponseDTO> propertyMapper1 = modelMapper.typeMap(Employee.class, EmpResponseDTO.class);
		propertyMapper1.addMapping(Employee::getEName, EmpResponseDTO::setEmpName);
		propertyMapper1.addMapping(Employee::getContactNum, EmpResponseDTO::setContactNumber);
		EmpResponseDTO empResponseDTO = modelMapper.map(employee, EmpResponseDTO.class);
		
		return empResponseDTO;
	}

	public List<EmpResponseDTO> getEmpList() { // list of entities converted into list of dto
		TypeMap<Employee, EmpResponseDTO> propertyMapper2 = this.modelMapper.createTypeMap(Employee.class,
				EmpResponseDTO.class);
		
		propertyMapper2.addMapping(Employee::getEName, EmpResponseDTO::setEmpName);
		propertyMapper2.addMapping(Employee::getContactNum, EmpResponseDTO::setContactNumber);
		
		return employeeRepository.findAll().stream().map(post -> modelMapper.map(post, EmpResponseDTO.class))
				.collect(Collectors.toList());
	}

	public EmpResponseDTO getEmployee(int id) throws EmployeeNotFoundException {

		Optional<Employee> employee=   employeeRepository.findById(id);
		if(!employee.isEmpty()){
			System.out.println("not null"+employee.toString());
			TypeMap<Employee, EmpResponseDTO> propertyMapper1 = modelMapper.typeMap(Employee.class, EmpResponseDTO.class);
			propertyMapper1.addMapping(Employee::getEName, EmpResponseDTO::setEmpName);
			propertyMapper1.addMapping(Employee::getContactNum, EmpResponseDTO::setContactNumber);
			EmpResponseDTO empResponseDTO = modelMapper.map(employee, EmpResponseDTO.class);
			return empResponseDTO;
        }else{
        	
            throw new EmployeeNotFoundException("employee not found with id : "+id);
        }
		
		
		
	}

}
