package com.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

	private String personId;
	private String Name;
	private int age;
	private String gender;
	private String contactNumber;
	private String Department;
	
}
