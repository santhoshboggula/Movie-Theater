package com.jpmc.theater.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDetails {
	private Movie movie;
	private int sequenceOfTheDay;
	private LocalDateTime showStartTime;
}
