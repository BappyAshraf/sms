package com.sms.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sms.user.service.UserService;
import com.sms.login.model.LoginInfo;
import com.sms.login.service.LoginService;
import com.sms.user.model.UserInfo;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserService userService;

	/*
	 * @RequestMapping(value = "/", method = RequestMethod.GET) public
	 * ModelAndView welcome(HttpSession session) { System.out.println("hello");
	 * 
	 * ModelAndView mav = new ModelAndView();
	 * 
	 * mav.setViewName("home"); return mav; }
	 */

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView welcome(HttpSession session) {
		 //System.out.println("hello");
		if (session.getAttribute("logonSuccessYN") == "Y") {
			ModelAndView mav = new ModelAndView();

			mav.setViewName("home");
			return mav;

		} else {
			return new ModelAndView("login");

		}
	}

	private void getmenu(HttpSession session) {

		if (session.getAttribute("logonSuccessYN") == "Y" && session.getAttribute("employeeid") != null) {

			session.setAttribute("sPersonalIdentification",
					loginService.GetPermittedMenuInfo((String) session.getAttribute("employeeid"), "M01"));

			/***********************
			 * Start Student
			 **********************************/

			/* Start student admit */
			session.setAttribute("sStudentAdmitMenu",
					loginService.GetPermittedMenuInfo((String) session.getAttribute("employeeid"), "M0202"));

			/* End student admit */

			/* Start student information */
			session.setAttribute("sStudentInformationMenu",
					loginService.GetPermittedMenuInfo((String) session.getAttribute("employeeid"), "M0201"));

			/* End student information */

			/*Start student promotion*/
			session.setAttribute("sStudentPromotionMenu",loginService.GetPermittedMenuInfo((String)session.getAttribute("employeeid"), "M0203"));
			
			/*End student promotion*/


			/**********************
			 * End Student
			 *****************************************************/

		}

	}

	@RequestMapping(value = "/validateUser", method = RequestMethod.POST, params = "submit")
	public ModelAndView validateUser(@ModelAttribute("loginInfo") LoginInfo loginInfo, BindingResult result,
			HttpServletRequest request, HttpServletResponse response, HttpSession session,
			final RedirectAttributes redirectAttributes) {
		// System.out.println("hello");
		try {
			// System.out.println("userName " + loginInfo.getUserName());
			// System.out.println("userPassword " + loginInfo.getPassword());
			LoginInfo oLoginInfo = new LoginInfo();
			String ip = request.getRemoteAddr();
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("X-Forwarded-For");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			loginInfo.setTerminalIp(ip);
			// System.out.println("IP "+ ip);

			// System.out.println(" username "+loginInfo.getUserName());
			// System.out.println(" pass "+loginInfo.getPassword());
			// System.out.println(" ip "+loginInfo.getTerminalIp());

			oLoginInfo = loginService.ValidateUser(loginInfo.getUserName(), loginInfo.getPassword(),
					loginInfo.getTerminalIp());

			//System.out.println("message SMS " + oLoginInfo.getMessage());

			if (!oLoginInfo.getMessage().equals(null) && oLoginInfo.getMessage().equals("1")) {

				session.setAttribute("username", loginInfo.getUserName());
				// session.setAttribute("password", oLoginInfo.getPassword());
				session.setAttribute("logindate", oLoginInfo.getLoginDate());
				session.setAttribute("branchCode", oLoginInfo.getBranchId());
				session.setAttribute("branchName", oLoginInfo.getBranchName());
				session.setAttribute("employeeid", oLoginInfo.getEmployeeId());
				session.setAttribute("designationId", oLoginInfo.getDesignationId());
				session.setAttribute("designationName", oLoginInfo.getDesignationName());
				session.setAttribute("userType", oLoginInfo.getUserType());
				session.setAttribute("formColour",
						"/resources/stylesheets/authorisationColor/" + oLoginInfo.getFormColour() + ".css");
				session.setAttribute("multipleBranch", oLoginInfo.getMultipleBranch());
				session.setAttribute("forceExpirePwd", oLoginInfo.getForceExpirePwd());
				session.setAttribute("delFlag", oLoginInfo.getDelFlag());

				if (oLoginInfo.getForceExpirePwd().equals("Y")) {
					session.setAttribute("logonSuccessYN", "N");
					return new ModelAndView("redirect:/changePassword");
				} else {
					session.setAttribute("logonSuccessYN", "Y");
				}

				/*
				 * System.out.println("logonSuccess " +
				 * session.getAttribute("logonSuccessYN")); System.out.println(
				 * "username " + loginInfo.getUserName()); System.out.println(
				 * "password " + oLoginInfo.getPassword()); System.out.println(
				 * "logindate " + oLoginInfo.getLoginDate());
				 * System.out.println("branchCode " + oLoginInfo.getBranchId());
				 * System.out.println("branchName " +
				 * oLoginInfo.getBranchName()); System.out.println("employeeid "
				 * + oLoginInfo.getEmployeeId()); System.out.println(
				 * "designationId " + oLoginInfo.getDesignationId());
				 * System.out.println("userType " + oLoginInfo.getUserType());
				 * System.out.println("formColour " +
				 * oLoginInfo.getFormColour()); System.out.println(
				 * "multipleBranch " + oLoginInfo.getMultipleBranch());
				 * System.out.println("forceExpirePwd " +
				 * oLoginInfo.getForceExpirePwd()); System.out.println(
				 * "delFlag " + oLoginInfo.getDelFlag());
				 */

				return new ModelAndView("redirect:/validateUser");
			} else {

				redirectAttributes.addFlashAttribute("displayMessage", "Invalid User Name or Password ");
				session.setAttribute("logonSuccessYN", "N");

				redirectAttributes.addFlashAttribute("loginInfo", oLoginInfo);
				return new ModelAndView("redirect:/");

			}

		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("displayMessage", e);
			return new ModelAndView("redirect:/");
		}

	}

	@RequestMapping(value = "validateUser", method = RequestMethod.GET)
	public ModelAndView RevalidateUser(@ModelAttribute("loginInfo") LoginInfo loginInfo, BindingResult result,
			ModelMap modelMap, HttpSession session, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			if (session.getAttribute("logonSuccessYN") == "Y") {

				String referrer = request.getHeader("referer");

				getmenu(session);

				return new ModelAndView("redirect:" + referrer);

			} else if (session.getAttribute("logonSuccessYN") == "N") {

				if ((session.getAttribute("forceExpirePwd").equals("Y"))) {
					//System.out.println("force " + session.getAttribute("forceExpirePwd"));
					return new ModelAndView("redirect:/changePassword");
				} else {
					//System.out.println("force2 " + session.getAttribute("forceExpirePwd"));
					modelAndView.addObject("displayMessage", "Log in to continue to SMS");
					modelAndView.addObject("loginInfo", loginInfo);
					modelAndView.setViewName("login");
				}
			} else {
				modelAndView.addObject("displayMessage", "Log in to continue to SMS");
				modelAndView.addObject("loginInfo", loginInfo);
				modelAndView.setViewName("login");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;

	}

	@RequestMapping(value = "changePassword", method = RequestMethod.GET)
	public ModelAndView changePassword(@ModelAttribute UserInfo userInfo, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = new ModelAndView();

		if (session.getAttribute("employeeid") != null) {
			// System.out.println("hello");
			if ((session.getAttribute("forceExpirePwd").equals("Y"))) {
				// System.out.println("hello1");
				modelAndView.setViewName("changePasswordFirst");
			} else {
				// System.out.println("hello2");
				modelAndView.setViewName("changePasswordInfo");
			}
			return modelAndView;
		} else {
			return new ModelAndView("redirect:/");
		}
	}

	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	public ModelAndView saveChangePassword(@ModelAttribute UserInfo userInfo, BindingResult result, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		if (session.getAttribute("employeeid") != null) {

			if (userInfo.getCurrentPassword() == "") {
				redirectAttributes.addFlashAttribute("message", "Enter Current Password !");
				redirectAttributes.addFlashAttribute("mCode", "0000");

			} else if (userInfo.getNewPassword() == "") {
				redirectAttributes.addFlashAttribute("message", "Enter New Password !");
				redirectAttributes.addFlashAttribute("mCode", "0000");

			}
			/*
			 * else if
			 * (userInfo.getCurrentPassword().equals(userInfo.getNewPassword()))
			 * { redirectAttributes.addFlashAttribute("message",
			 * "No changes are applicable !");
			 * redirectAttributes.addFlashAttribute("mCode", "0000");
			 * 
			 * }
			 */
			else if (!userInfo.getNewPassword().equals(userInfo.getConfirmPassword())) {
				redirectAttributes.addFlashAttribute("message", "New password and confirm password did not match !");
				redirectAttributes.addFlashAttribute("mCode", "0000");

			} else {
				UserInfo oUserInfo = new UserInfo();
				userInfo.setEmployeeId((String) session.getAttribute("employeeid"));
				userInfo.setUpdateBy((String) session.getAttribute("employeeid"));
				oUserInfo = userService.userPasswordChng(userInfo);

				redirectAttributes.addFlashAttribute("message", oUserInfo.getMessage());
				redirectAttributes.addFlashAttribute("mCode", oUserInfo.getMessageCode());

				if (oUserInfo.getMessageCode().equals("1111")) {
					session.setAttribute("logonSuccessYN", "Y");
					getmenu(session);
					return new ModelAndView("redirect:/");
				}
			}
		}

		return new ModelAndView("redirect:/changePassword");
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {

		try {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return new ModelAndView("redirect:/login");
	}

}
