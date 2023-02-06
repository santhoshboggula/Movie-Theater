package com.jpmc.theater.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {

	@NotNull(message = "Please provide a customer details")
	private Customer customer;

	@Min(value = 1, message = "Sequence should be greater than 1")
	private int sequence;

	@Min(value = 1, message = "Seat number should be greater than 1")
	private int numberOfTickets;
}
