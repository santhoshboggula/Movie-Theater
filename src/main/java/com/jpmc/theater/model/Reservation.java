package com.jpmc.theater.model;//package com.jpmc.theater;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
	private Customer customer;
	private ShowDetails showDetails;
	private int audienceCount;
	private double totalAmount;
}