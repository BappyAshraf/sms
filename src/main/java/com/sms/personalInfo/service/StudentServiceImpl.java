package com.sms.personalInfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.personalInfo.dao.StudentDao;
import com.sms.personalInfo.model.Student;


@Service("studentService")
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDao studentDao;


	public Student saveStudentInfo(Student student) {
		return studentDao.saveStudentInfo(student);
	}


	

}
