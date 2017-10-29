<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.sms.login.model.MenuInfo"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>

<!-- START PAGE SIDEBAR -->

<div class="sidebar-header">
	<!-- <div class="sidebar-title">
							Navigation
						</div> -->
	<div class="sidebar-toggle hidden-xs"
		data-toggle-class="sidebar-left-collapsed" data-target="html"
		data-fire-event="sidebar-left-toggle">
		<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
	</div>
</div>

<div class="nano">
	<div class="nano-content">
		<nav id="menu" class="nav-main" role="navigation">
			<ul class="nav nav-main">
				<li
					class="nav-parent<%if (request.getAttribute("javax.servlet.forward.request_uri").equals("/sms/validateUser")
					|| request.getAttribute("javax.servlet.forward.request_uri").equals("/sms/")
					|| request.getAttribute("javax.servlet.forward.request_uri").equals("/sms/login")) {
				out.print(" nav-expanded nav-active");
			}%>">
					<a href="/sms/"> <i class="fa fa-home" aria-hidden="true"></i>
						<span>Dashboard</span>
				</a>
				</li>



				<%
					List<MenuInfo> personalIdentificationMenuInfo = (List<MenuInfo>) session
							.getAttribute("sPersonalIdentification");
					if (!personalIdentificationMenuInfo.isEmpty()) {
						Iterator it = personalIdentificationMenuInfo.iterator();

						while (it.hasNext()) {
							MenuInfo pIdentificationMenuInfoList = (MenuInfo) it.next();
				%>
				<li
					class="<%if (request.getAttribute("javax.servlet.forward.request_uri")
							.equals(pIdentificationMenuInfoList.getMenuActionName())) {
						out.print("nav-active");
					}%>">
					<a href=<%=pIdentificationMenuInfoList.getMenuActionName()%>><i
						class="fa fa-male" aria-hidden="true"></i><span><%=pIdentificationMenuInfoList.getMenuName()%></span></a>
				</li>

				<%
					}
				%>
				<%
					}
				%>


				<!--start System Administration  -->

				<%
					List<MenuInfo> studentAdmitMenuInfo = (List<MenuInfo>) session.getAttribute("sStudentAdmitMenu");
					List<MenuInfo> studentInformationMenuInfo = (List<MenuInfo>) session
							.getAttribute("sStudentInformationMenu");
					List<MenuInfo> studentPromotionMenuInfo = (List<MenuInfo>) session.getAttribute("sStudentPromotionMenu");

					if (!studentAdmitMenuInfo.isEmpty() || !studentInformationMenuInfo.isEmpty()
							|| !studentPromotionMenuInfo.isEmpty()) {
				%>

				<li
					class="nav-parent<%if (request.getAttribute("javax.servlet.forward.request_uri")
						.equals("/sms/student/studentInformation/classOne")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/sms/student/studentInformation/classTwo")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/sms/student/studentInformation/classThree")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/sms/student/studentInformation/classFour")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/sms/student/studentInformation/classFive")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/sms/student/studentInformation/classSix")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/sms/student/studentInformation/classSeven")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/sms/student/studentInformation/classEight")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/sms/student/studentInformation/classNine")
						|| request.getAttribute("javax.servlet.forward.request_uri")
								.equals("/sms/student/studentInformation/classTen")) {
					out.print(" nav-expanded nav-active");
				}%>">
					<a> <i class="fa fa-gears" aria-hidden="true"></i> <span>Student</span>
				</a>
					<ul class="nav nav-children">

						<!--Start Change Request Administration  -->

						<%
							if (!studentInformationMenuInfo.isEmpty()) {
						%>
						<li
							class="nav-parent<%if (request.getAttribute("javax.servlet.forward.request_uri")
							.equals("/sms/student/studentInformation/classOne")
							|| request.getAttribute("javax.servlet.forward.request_uri")
									.equals("/sms/student/studentInformation/classTwo")
							|| request.getAttribute("javax.servlet.forward.request_uri")
									.equals("/sms/student/studentInformation/classThree")
							|| request.getAttribute("javax.servlet.forward.request_uri")
									.equals("/sms/student/studentInformation/classFour")
							|| request.getAttribute("javax.servlet.forward.request_uri")
									.equals("/sms/student/studentInformation/classFive")
							|| request.getAttribute("javax.servlet.forward.request_uri")
									.equals("/sms/student/studentInformation/classSix")
							|| request.getAttribute("javax.servlet.forward.request_uri")
									.equals("/sms/student/studentInformation/classSeven")
							|| request.getAttribute("javax.servlet.forward.request_uri")
									.equals("/sms/student/studentInformation/classEight")
							|| request.getAttribute("javax.servlet.forward.request_uri")
									.equals("/sms/student/studentInformation/classNine")
							|| request.getAttribute("javax.servlet.forward.request_uri")
									.equals("/sms/student/studentInformation/classTen")) {
						out.print(" nav-expanded nav-active");
					}%>">
							<a> <i class="fa fa-list-alt" aria-hidden="true"></i> <span>Student
									Information</span>
						</a>
							<ul class="nav nav-children">


								<%
									if (!studentInformationMenuInfo.isEmpty()) {

												Iterator it = studentInformationMenuInfo.iterator();

												while (it.hasNext()) {
													MenuInfo studentInformationMenuInfoList = (MenuInfo) it.next();
								%>
								<li
									class="<%if (request.getAttribute("javax.servlet.forward.request_uri")
									.equals(studentInformationMenuInfoList.getMenuActionName())) {
								out.print("nav-active");
							}%>">
									<a href=<%=studentInformationMenuInfoList.getMenuActionName()%>><%=studentInformationMenuInfoList.getMenuName()%></a>
								</li>


								<%
									}
								%>

								<%
									}
								%>
							</ul>
						</li>
						<%
							}
						%>

						<!--end Change Request Administration  -->


						<!--Start admit student  -->
						<%
							if (!studentAdmitMenuInfo.isEmpty()) {

									Iterator it = studentAdmitMenuInfo.iterator();

									while (it.hasNext()) {
										MenuInfo studentAdmitMenuInfoList = (MenuInfo) it.next();
						%>

						<li
							class="<%if (request.getAttribute("javax.servlet.forward.request_uri")
								.equals(studentAdmitMenuInfoList.getMenuActionName())) {
							out.print("nav-active");
						}%>">
							<a href=<%=studentAdmitMenuInfoList.getMenuActionName()%>><i
								class="fa fa-list-alt" aria-hidden="true"></i> <%=studentAdmitMenuInfoList.getMenuName()%></a>
						</li>


						<%
							}
						%>

						<%
							}
						%>

						<!--End admit student  -->

						<!--Start promotion student  -->
						<%
							if (!studentPromotionMenuInfo.isEmpty()) {

									Iterator it = studentPromotionMenuInfo.iterator();

									while (it.hasNext()) {
										MenuInfo studentPromotionMenuInfoList = (MenuInfo) it.next();
						%>

						<li
							class="<%if (request.getAttribute("javax.servlet.forward.request_uri")
								.equals(studentPromotionMenuInfoList.getMenuActionName())) {
							out.print("nav-active");
						}%>">
							<a href=<%=studentPromotionMenuInfoList.getMenuActionName()%>><i
								class="fa fa-list-alt" aria-hidden="true"></i> <%=studentPromotionMenuInfoList.getMenuName()%></a>
						</li>


						<%
							}
						%>

						<%
							}
						%>

						<!--End promotion student  -->


					</ul>
				</li>
				<%
					}
				%>
				<!-- end student -->
			</ul>
		</nav>

	</div>

</div>

<!-- END PAGE SIDEBAR -->