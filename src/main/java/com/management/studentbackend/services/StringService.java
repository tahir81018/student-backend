package com.management.studentbackend.services;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class StringService {

	public String toFirstUpperCase(String txt) {
		if (txt != "" && txt != null) {
			txt = txt.trim();
			txt = txt.substring(0, 1).toUpperCase() + txt.substring(1);
		}
		return txt;
	}
}
