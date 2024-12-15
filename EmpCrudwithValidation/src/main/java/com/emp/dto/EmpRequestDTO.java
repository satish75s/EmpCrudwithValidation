package com.emp.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpRequestDTO {

	@NotNull(message = "username shouldn't be null")
	private String empName;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date doj;
	@Size(min = 1, max = 1)
	private String gender;
	
	@NotNull(message = "Please enter salary")
	private float salray;
	
	private boolean isIndian;
	@Pattern(regexp = "^\\d{10}$", message = "invalid mobile number entered ")
	private String contactNumber;
	@Email(message = "invalid email address")
	private String email;

}
