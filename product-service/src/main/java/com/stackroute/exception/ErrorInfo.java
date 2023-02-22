package com.stackroute.exception;

import java.io.Serializable;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;
	private LocalDateTime time;
}
