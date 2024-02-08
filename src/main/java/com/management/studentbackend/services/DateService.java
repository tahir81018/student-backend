package com.management.studentbackend.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateService {

	public Date getDueDate(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		Date dueDate = calendar.getTime();
		return dueDate;
	}

	public int getMonthsBetween(Date date1, Date date2) {
		if (date1.compareTo(date2) > 0) {
			long timeInMillis = date2.getTime() - date1.getTime();
			int timeInMonths = (int) (timeInMillis / (1000 * 60 * 60 * 24 * 30));
			return timeInMonths;
		} else if (date1.compareTo(date2) == 0) {
			return 0;
		} else {
			long timeInMillis = date1.getTime() - date2.getTime();
			int timeInMonths = (int) (timeInMillis / (1000 * 60 * 60 * 24 * 30));
			return timeInMonths;
		}
	}

}
