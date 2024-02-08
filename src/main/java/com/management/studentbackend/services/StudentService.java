package com.management.studentbackend.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.studentbackend.entities.Student;
import com.management.studentbackend.enums.Status;
import com.management.studentbackend.models.Response;
import com.management.studentbackend.repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	DateService dateService;

	@Autowired
	StringService stringService;

	public List<Student> getStudents() {
		List<Student> students = studentRepository.findAll();
		for (int i = 0; i < students.size(); i++) {
			Student student = students.get(i);
			Date startDate = student.getStartDate();
			Date dueDate = student.getDueDate();
			int totalMonths = dateService.getMonthsBetween(startDate, new Date());
			int due = (totalMonths * student.getPayble()) - student.getPaid();

			if (dueDate.compareTo(new Date()) <= 0) {
				Date newDueDate = dateService.getDueDate(dueDate);
				student.setDueDate(newDueDate);
			}
			student.setMonths(totalMonths);
			student.setDue(due);
			studentRepository.save(student);
		}
		return students;
	}

	public Student getStudent(int id) {
		return studentRepository.findById(id).orElseThrow();
	}

	public Response saveStudent(Student student, String msg) {
		try {
			student.setFname(stringService.toFirstUpperCase(student.getFname()));
			student.setMname(stringService.toFirstUpperCase(student.getMname()));
			student.setLname(stringService.toFirstUpperCase(student.getLname()));
			student.setClassName(stringService.toFirstUpperCase(student.getClassName()));
			student.setDueDate(dateService.getDueDate(student.getStartDate()));
			studentRepository.save(student);
			return new Response(Status.SUCCESS, msg);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalError();
		}
	}

	public Response deleteStudent(Student student) {
		try {
			studentRepository.delete(student);
			return new Response(Status.SUCCESS, "Student has been deleted");
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalError();
		}
	}

	public Response updatePayment(Student student, int payble) {
		try {
			student.setPaid(student.getPaid() + payble);
			studentRepository.save(student);
			return new Response(Status.SUCCESS, "Payment added");
		} catch (Exception e) {
			throw new InternalError();
		}
	}

}
