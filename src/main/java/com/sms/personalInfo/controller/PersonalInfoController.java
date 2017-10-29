package com.sms.personalInfo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aes.protection.CipherUtils;
import com.sms.lookUp.model.LookupModel;
import com.sms.lookUp.service.LookupService;
import com.sms.personalInfo.model.PersonalInfo;
import com.sms.personalInfo.model.Student;
import com.sms.personalInfo.service.StudentService;



@RequestMapping(value = "student")
@Controller
public class PersonalInfoController {
	
	
	CipherUtils oCipherUtils = new CipherUtils();
	@Autowired
	private LookupService lookupService;
	@Autowired
	private StudentService studentService;
	
	
	
	@RequestMapping(value = "studentInfo", method = RequestMethod.GET)
	public ModelAndView personalInfo(HttpSession session,LookupModel lookupModel,PersonalInfo personalInfo) {
			if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView("personalIdentification");
				return mav;
			}else {
			return new ModelAndView("redirect:/login");
		}
	}
	
	// ============== Save Student Info =============//

	@RequestMapping(value = "saveStudentInfo", method = RequestMethod.POST)
	public ModelAndView saveStudentInfo(@ModelAttribute Student student, HttpSession session,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		//System.out.println("entrance");
		if (session.getAttribute("logonSuccessYN") == "Y") {
			//System.out.println("name " + student.getStudentName());
			//System.out.println("mobile " + student.getMobileNo());
			//System.out.println("email " + student.getEmailId());
			//System.out.println("photo " + student.getStudentAttachmentFile());
			try {
				String attachmentPath = "";
				String attachmentName = "";

				MultipartFile attachment = student.getStudentAttachmentFile();
				 //System.out.println("attachmentFile " + student.getStudentAttachmentFile());
				 //System.out.println("attachment.getSize " + attachment.getSize());

				if (attachment.getSize() > 614400) {
					redirectAttributes.addFlashAttribute("message", "File Must be less than 600 kb !!!");
					redirectAttributes.addFlashAttribute("mCode", "0000");
				} else {

					String fileExt = FilenameUtils.getExtension(attachment.getOriginalFilename());

					if (attachment.getSize() <= 614400) {
						student.setStudentAttachmentName("student");
						student.setStudentAttachmentExt(fileExt);
					}

					Student oStudent = new Student();
					oStudent = studentService.saveStudentInfo(student);

					redirectAttributes.addFlashAttribute("message", oStudent.getMessage());
					redirectAttributes.addFlashAttribute("mCode", oStudent.getMessageCode());

					InputStream inputStream = null;
					OutputStream outputStream = null;

					if (attachment.getSize() <= 614400 && oStudent.getMessageCode().equals("1111")) {

						if (attachment.getSize() > 0) {
							inputStream = attachment.getInputStream();
							attachmentPath = "/resources/attachment/studentPhoto";
							String path = request.getSession().getServletContext().getRealPath(attachmentPath);
							 //System.out.println("path " + path);
							File uploadRootDir = new File(path);
							// Create directory if it not exists.
							if (!uploadRootDir.exists()) {
								 //System.out.println("uploadRootDir " + uploadRootDir);
								uploadRootDir.mkdirs();
							}

							if (oStudent.getStudentId() != null) {
								outputStream = new FileOutputStream(uploadRootDir.getAbsolutePath() + File.separator
										+ student.getStudentAttachmentName() + "_"
										+ student.getStudentName() + "_"
										+ oStudent.getStudentId() + "." + fileExt);
								// System.out.println("outputStream 1 " +
								// outputStream);
							} else {
								outputStream = new FileOutputStream(uploadRootDir.getAbsolutePath() + File.separator
										+ student.getStudentAttachmentName() + "_"
										+ student.getStudentName() + "_"
										+ oStudent.getStudentId() + "." + fileExt);
								// System.out.println("outputStream 2 " +
								// outputStream);
							}

							int readBytes = 0;
							byte[] buffer = new byte[8192];
							while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
								outputStream.write(buffer, 0, readBytes);
							}
							outputStream.close();
							inputStream.close();
						}
					}
				}
				redirectAttributes.addFlashAttribute("student", student);
				return new ModelAndView("redirect:/student/studentInfo");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("mCode", "0000");
				redirectAttributes.addFlashAttribute("message", "Error occured!!");
				redirectAttributes.addFlashAttribute("student", student);
				return new ModelAndView("redirect:/student/studentInfo");
			}
		} else {
			return new ModelAndView("redirect:/login");
		}
	}	
	

		
	

}
