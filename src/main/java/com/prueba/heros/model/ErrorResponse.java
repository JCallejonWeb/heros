package com.prueba.heros.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
	private int httpStatus;
	private String message;
}
