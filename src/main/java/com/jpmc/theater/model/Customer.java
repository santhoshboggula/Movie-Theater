package com.jpmc.theater.model;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@NotEmpty(message = "Please provide a customer name")
	private String name;
	private String id;
}
