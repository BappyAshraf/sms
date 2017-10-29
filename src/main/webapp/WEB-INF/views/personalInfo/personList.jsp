<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${!empty personList}">
	<c:forEach items="${personList}" var="list">
		<tr style="cursor: pointer"
			onclick="selectRegistrationNoList('${list.personId}')">
			<td style="border-style: inset;">${list.personName}</td>
			<td style="border-style: inset;">${list.fatherName}</td>
			<td style="border-style: inset;">${list.dateOfBirth}</td>
		<tr>
	</c:forEach>
</c:if>
<c:if test="${! empty personNotFound}">
	<tr>
		<td colspan="8"><p>${personNotFound}</p></td>
	</tr>
</c:if>