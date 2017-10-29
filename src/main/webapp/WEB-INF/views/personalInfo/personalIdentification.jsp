<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<header class="page-header">
	<!-- <h2>Personal Identification</h2> -->

	<div class="left-wrapper pull-left">
		<ol class="breadcrumbs">
			<li><a href="/sms/"> &nbsp;<i class="fa fa-home"></i></a></li>
			<li><span>Student Identification &nbsp;</span></li>
		</ol>
	</div>
</header>



<!-- start: page -->

<div class="row">
	<div class="col-lg-12">

		<!-- message -->

		<c:if test="${!empty message}">
			<div
				class="alert
						<c:choose>
						<c:when test="${mCode == '0000'}"> <!-- false -->
						alert-danger 
						</c:when>
						<c:when test="${mCode == '1111'}"> <!-- true -->
						alert-success
						</c:when>
						<c:otherwise>
						alert-primary
						</c:otherwise>
						</c:choose>
						
						">

				<button class="close" aria-hidden="true" data-dismiss="alert"
					type="button">&times;</button>
				<strong>${message}</strong>
			</div>
		</c:if>

		<!-- message -->





		<form class="form-horizontal form-bordered" method="post" enctype="multipart/form-data"
			action="saveStudentInfo" id="student"
			onkeypress="return event.keyCode != 13;">
			<section class="panel">
				<header class="panel-heading" style="padding: 7px; margin: 0px">
					<h2 class="panel-title">Student Identification</h2>
				</header>

				<div class="panel-body">
					<div class="row">
						<div class=" col-md-12 ">
							<div class="form-group col-md-6">

								<div class="form-group col-md-12">
									<label class="col-md-5 control-label" for="personName">Student
										Name </label>
									<div class="col-md-5">
										<input type="text" class="form-control mandatory upercase"
											name="studentName" required id="studentName"
											value="${student.studentName}"
											onkeypress="goToNext(event,'lastName')">
									</div>
								</div>


								<div class="form-group col-md-12">
									<label class="col-md-5 control-label" for="peFax">Upload
										Photo</label>
									<div class="col-md-5">
										<input type="file" id="studentAttachmentFile"
											name="studentAttachmentFile"
											value="${student.studentAttachmentFile}">
									</div>
									<div class="form-group col-md-6"></div>
								</div>

							</div>


							<div class="form-group col-md-6">

								<div class="form-group col-md-12">
									<label class="col-md-5 control-label" for="mobileNo">Mobile
										No</label>
									<div class="col-md-5">
										<input type="text" name="mobileNo"
											class="form-control mandatory number" id="mobileNo" required
											value="${student.mobileNo}"
											onkeypress="return isNumber(event,this)">
									</div>
								</div>

								<div class="form-group col-md-12">
									<label class="col-md-5 control-label" for="emailId">E-mail</label>
									<div class="col-md-5">
										<input type="text" class="form-control" name="emailId"
											id="emailId" value="${student.emailId}"
											onkeypress="goToNext(event,'gender')">
									</div>
								</div>

							</div>
						</div>

					</div>
				</div>


				<footer class="panel-footer">
					<div class="row">
						<div class="btn_div col-sm-offset-0 ">
							<button class="btn btn-primary" type="submit">Save</button>
							<a href="studentInfo"><button
									class="btn btn-primary" type="button" role="button">Clear</button></a>
						</div>

					</div>
				</footer>


			</section>
		</form>



	</div>
</div>
<!----------------------------------------------------- Start person Id Modal ----------------------------------------------------------------------->


<div id="modalPersonList" class="modal-block modal-block-full mfp-hide">
	<!-- class="zoom-anim-dialog modal-block modal-block-full mfp-hide"> -->
	<section class="panel">
		<header class="panel-heading">
			<button class="close modal-dismiss" type="button"
				id="btnModalEmpListClose">
				<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
			</button>
			<h2 class="panel-title">Person Search</h2>
		</header>

		<div class="panel-body">

			<div class="col-md-6">
				<label class="col-md-4 control-label" for="inputReadOnly">Person
					Id</label>
				<div class="col-md-6">
					<input type="text" class="form-control" id="personId2">
				</div>
			</div>


			<div class="col-md-6">
				<label class="col-md-4 control-label" for="inputReadOnly">Person
					Name</label>
				<div class="col-md-6">
					<input type="text" class="form-control" id="personName2">
				</div>
			</div>

			<div class="col-md-6">
				<label class="col-md-4 control-label" for="inputReadOnly">Fathers
					Name</label>
				<div class="col-md-6">
					<input type="text" class="form-control" id="fathersName2">

				</div>
			</div>

			<div class="col-md-6">
				<label class="col-md-4 control-label" for="modalDateOfBirth">Date
					Of Birth</label>
				<div class="col-md-6">
					<input name="dateOfBirth" id="dateOfBirth" type="text"
						data-plugin-masked-input data-input-mask="99/99/9999"
						placeholder="dd/mm/yyyy" class="form-control"
						value="${personalInfo.dateOfBirth}">

				</div>
			</div>






			<div class="col-sm-12" style="margin-bottom: 10px">
				<div id="registrationModalmsg" align="center"></div>
				<div class="text-right">
					<br />
					<button class="btn btn-animate btn-animate-side btn-primary"
						type="button" onclick="clearPersonModal()">
						<span><i class="icon wb-arrow-down" aria-hidden="true"></i>
							Refresh </span>
					</button>
					<button class="btn btn-animate btn-animate-side btn-primary"
						type="button" onclick="getPersonList()">
						<span><i class="icon wb-arrow-down" aria-hidden="true"></i>
							Search </span>
					</button>
				</div>
			</div>


			<div class="row">
				<div class="col-md-12">
					<div style="overflow: scroll; max-height: 300px;">

						<table
							class="table table-striped table-condensed table-hover mb-none ">
							<thead>
								<tr>
									<th>Person ID</th>
									<th>Person Name</th>
									<th>Father's Name</th>
									<th>Date of Birth</th>

								</tr>
							</thead>
							<tbody id="personList" style="border-style: inset;">

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

	</section>
</div>

<!-------------------------------- Ending Modal --------------------------------------------->


<script
	src="<c:url value="/resources/javascripts/googleapi.jquery.min.js" />"></script>
<script>


 function isNumber(evt,elem) {
	// var msg = "<label for='"+elem.id+"' class='error'>Please enter a valid number.</label>";
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
    	
    	//$(msg).insertAfter(elem);
    	//alert(elem.id)
    			return false;
    }
    return true;
}
 



function updateSelectOptions(link, parentSelectElementId,
		childSelectElementId) {
	var parentSelectElement = $("#" + parentSelectElementId).val();

	//alert('fff');
	//alert(parentSelectElement);
	if (parentSelectElement == '') {
		$("#" + childSelectElementId + "option").remove();
		$("#" + childSelectElementId).val('');
		var content = '<option value="">Please Select One</option>';
		$('#' + childSelectElementId).html(content);
		//$("#select2-roleId-container").html('Please Select One');
	} else {

		//alert(parentSelectElement);
		//alert(childSelectElementId);

		$.ajax({
			type : "get",
			url : link,
			data : "searchId=" + parentSelectElement,
			async : true,
			success : function(data) {
			//	alert('success');
				$("#" + childSelectElementId).html(data);
				
			}

		});
		
	

		$("#" + childSelectElementId).focus();
	}
};

/* 
function copyTextValue(v) {
	alert("lkjkl");
	
	  if(v.village.checked == true) {
		  alert('village');
		  
		 v.peCountryId.value = v.prCountryId.value;
		  v.peStreet.value = v.prStreet.value;
		  v.peVillage.value = v.prVillage.value;
		  v.peDivisionId.value = v.prDivisionId.value;
		 v.peDistrictId.value = v.prDistrictId.value;
		 v.peThanaId.value = v.prThanaId.value;
		  v.pePostCode.value = v.prPostCode.value;
		  v.peFax.value = v.prFax.value;
		  v.peTelephone.value = v.prTelephone.value;
		  
		  alert(v.peCountryId.value);
	  }
	}
 */


/* function copyTextValue(village) {

	var peCountryId = document.getElementById("peCountryId").value;
	var peStreet = document.getElementById("peStreet").value;
	var peVillage = document.getElementById("peVillage").value;
	var peDivisionId = document.getElementById("peDivisionId").value;
	var peDistrictId = document.getElementById("peDistrictId").value;
	var peThanaId = document.getElementById("peThanaId").value;
	var pePostCode = document.getElementById("pePostCode").value;
	var peFax = document.getElementById("peFax").value;
	var peTelephone = document.getElementById("peTelephone").value;
	
	
	
	
	
	if (village.checked) {
		$('#prCountryId').attr('disabled', 'disabled');
	 }
	else {
		peCountryId = '';
		$('#prCountryId').removeAttr('disabled');
	}
	
	if (village.checked) {
		$('#prStreet').attr('disabled', 'disabled');
	 }
	else {
		peStreet = '';
		$('#prStreet').removeAttr('disabled');
	}
	
	if (village.checked) {
		$('#prVillage').attr('disabled', 'disabled');
	 }
	else {
		peVillage = '';
		$('#prVillage').removeAttr('disabled');
	}
	
	if (village.checked) {
		$('#prDivisionId').attr('disabled', 'disabled');
	 }
	else {
		peDivisionId = '';
		$('#prDivisionId').removeAttr('disabled');
	}
	
	if (village.checked) {
		$('#prDistrictId').attr('disabled', 'disabled');
	 }
	else {
		peDistrictId = '';
		$('#prDistrictId').removeAttr('disabled');
	}
	
	if (village.checked) {
		$('#prThanaId').attr('disabled', 'disabled');
	 }
	else {
		peThanaId = '';
		$('#prThanaId').removeAttr('disabled');
	}
	if (village.checked) {
		$('#prPostCode').attr('disabled', 'disabled');
	 }
	else {
		pePostCode = '';
		$('#prPostCode').removeAttr('disabled');
	}
	if (village.checked) {
		$('#prFax').attr('disabled', 'disabled');
	 }
	else {
		peFax = '';
		$('#prFax').removeAttr('disabled');
	}
	if (village.checked) {
		$('#prTelephone').attr('disabled', 'disabled');
	 }
	else {
		peTelephone = '';
		$('#prTelephone').removeAttr('disabled');
	}
	
	document.getElementById("prCountryId").value.hidden = peCountryId;
	document.getElementById("prStreet").value.hidden = peStreet;
	document.getElementById("prVillage").value.hidden = peVillage;
	document.getElementById("prDivisionId").value.hidden = peDivisionId;
	document.getElementById("prDistrictId").value.hidden = peDistrictId;
	document.getElementById("prThanaId").value.hidden = peThanaId;
	document.getElementById("prFax").value.hidden = peFax;
	document.getElementById("prTelephone").value.hidden = peTelephone;

};
 */
 $(document).ready( function() {
		
		$('#genderId option[value='+ ${personalInfo.genderId}  +']').prop("selected",true);
		$('#nationalityId option[value='+ ${personalInfo.nationalityId}  +']').prop("selected",true);
		$('#bloodId option[value='+ ${personalInfo.bloodId}  +']').prop("selected",true);
		$('#religionId option[value='+ ${personalInfo.religionId}  +']').prop("selected",true);
		$('#peCountryId option[value='+ ${personalInfo.peCountryId}  +']').prop("selected",true);
		$('#peDivisionId option[value='+ ${personalInfo.peDivisionId}  +']').prop("selected",true);
		$('#peJuridictionId option[value='+ ${personalInfo.peJuridictionId}  +']').prop("selected",true);
		$('#peThanaId option[value='+ ${personalInfo.peThanaId}  +']').prop("selected",true);
		$('#prDivisionId option[value='+ ${personalInfo.prDivisionId}  +']').prop("selected",true);
		$('#prCountryId option[value='+ ${personalInfo.prCountryId}  +']').prop("selected",true);
		$('#prJuridictionId option[value='+ ${personalInfo.prJuridictionId}  +']').prop("selected",true);
		$('#prThanaId option[value='+ ${personalInfo.prThanaId}  +']').prop("selected",true);
		
		var encEmpId = $("#encPersonId1").val();
		
		$('#personId').focus();
		if(encEmpId != ''){
			getEmpBranchList(encEmpId);
			$("#savePersonalInfo").html("Update");
		}
	});

function getPersonList() {
	var personId = $("#personId2").val();
	var personName = $("#personName2").val();
	var fathersName = $("#fathersName2").val();
	var dateOfBirth = $("#dateOfBirth2").val();
	var requiredfield = "Minimum One Value of Criteria is Required!!!";

	if (personId == '' && personName == '' && fathersName == ''
			&& dateOfBirth == '') {
		$("#registrationModalmsg").html(
				'<span style="color:red">' + requiredfield + '<span>');
		alert('Minimum One Value of Criteria is Required!!!');
	} else {

		var link = "/brtais/personalInfo/getPersonList";
		$.ajax({
			type : "POST",
			url : link,
			data : "personId=" + personId + "&personName="
					+ personName + "&fathersName=" + fathersName
					+ "&dateOfBirth=" + dateOfBirth,

			/* {"${_csrf.parameterName}":"${_csrf.token}"}, */

			async : true,

			success : function(data) {
				// alert('success')
				$("#personList").html(data);
			},
			error : function(data) {
				alert('Error!!!')
			}
		});

	}
}





</script>


<!-- end: page -->