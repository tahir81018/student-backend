package com.management.studentbackend.models;

import com.management.studentbackend.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response {

	Status status;
	String message;
}
