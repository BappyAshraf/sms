package com.sms.personalInfo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.aes.protection.CipherUtils;
import com.sms.personalInfo.model.PersonalInfo;
import com.sms.personalInfo.model.Student;


@Repository("")


public class StudentDaoImpl implements StudentDao{
	
	private SimpleJdbcCall simpleJdbcCall;
	private JdbcTemplate jdbcTemplate;
	//RemoveNull oRemoveNull = new RemoveNull();
	CipherUtils oCipherUtils = new CipherUtils();
	@Autowired
	private DataSource dataSource;

	public Student saveStudentInfo(Student student) {

		jdbcTemplate = new JdbcTemplate(dataSource);
		Student oStudent = new Student();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PRO_STUDENT_INFO_SAVE");

			Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("P_STUDENT_ID", student.getStudentId());
			inParamMap.put("P_STUDENT_NAME", student.getStudentName());
			inParamMap.put("P_MOBILE_NO", student.getMobileNo());
			inParamMap.put("P_EMAIL_ID", student.getEmailId());
			inParamMap.put("P_STUDENT_FILE_NAME", student.getStudentAttachmentName());
			inParamMap.put("P_STUDENT_FILE_EXT", student.getStudentAttachmentExt());

			Map<String, Object> outParamMap = simpleJdbcCall.execute(new MapSqlParameterSource().addValues(inParamMap));

			oStudent.setMessage((String) outParamMap.get("P_MESSAGE"));
			oStudent.setMessageCode((String) outParamMap.get("P_MESSAGE_CODE"));

		} catch (Exception ex) {
			oStudent.setMessageCode("0000");
			oStudent.setMessage("Error Saving Records Try Again!!!");
			ex.printStackTrace();
		}
		return oStudent;
	}
	
		
		
	
	
}
