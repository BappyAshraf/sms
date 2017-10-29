package com.sms.lookUp.service;

import java.util.List;

import com.sms.lookUp.model.LookupModel;



public interface LookupService {

	public List<LookupModel> approvalAuthorList(LookupModel lookupModel) ;
	
	public List<LookupModel> branchList(LookupModel lookupModel);

	public List<LookupModel> designationList(LookupModel lookupModel);

	public List<LookupModel> genderList(LookupModel lookupModel);

	public List<LookupModel> relegionList(LookupModel lookupModel);

	public List<LookupModel> maritalStatusList(LookupModel lookupModel);

	public List<LookupModel> nationalityList(LookupModel lookupModel);

	public List<LookupModel> crntAuthorityList(LookupModel lookupModel);

	public List<LookupModel> fitnessTypeList(LookupModel lookupModel);

	public List<LookupModel> currentFuelTypeList(LookupModel lookupModel);

	public List<LookupModel> resultList(LookupModel lookupModel);

	public List<LookupModel> reportListLoad(String sReportType);

	public List<LookupModel> vehicleClassList(LookupModel lookupModel);

	public List<LookupModel> routePermitTypeList(LookupModel lookupModel);

	public List<LookupModel> licenseTypeList(LookupModel lookupModel);

	public List<LookupModel> refusalCodeList(LookupModel lookupModel);

	public List<LookupModel> registrationAreaList(LookupModel lookupModel);

	public List<LookupModel> ownershipTypeList(LookupModel lookupModel);

	public List<LookupModel> hireList(LookupModel lookupModel);

	public List<LookupModel> surrenderTypedList(LookupModel lookupModel);

	public List<LookupModel> getCertificateIssuedByList(LookupModel lookupModel);

	public List<LookupModel> acceptanceList(LookupModel lookupModel);

	public List<LookupModel> carraigeTypeList(LookupModel lookupModel);

	public List<LookupModel> timeTableList(LookupModel lookupModel);

	public List<LookupModel> approvalAuthorityList(LookupModel lookupModel);

	public List<LookupModel> cbuSkdCkdList(LookupModel lookupModel);

	public List<LookupModel> applicationTypeList(LookupModel lookupModel);

	public List<LookupModel> examinerList(LookupModel lookupModel);

	public List<LookupModel> licenseClassList(LookupModel lookupModel);

	public List<LookupModel> GarageList(LookupModel lookupModel);
	public List<LookupModel> countryList(LookupModel lookupModel);
	/*public List<LookupModel> thanaList(LookupModel lookupModel);*/
	
	/*----------------------------------- Start Type Approval/CKD---------------------------*/
	public List<LookupModel> typeAppIdSearchList	(LookupModel lookupModel);
	
	public List<LookupModel> vehicleTypeList(LookupModel lookupModel);

	public List<LookupModel> companyList(LookupModel lookupModel);

	public List<LookupModel> manufacturerList(LookupModel lookupModel);

	public List<LookupModel> fuelTypeList(LookupModel lookupModel);

	public List<LookupModel> brakeSystemList(LookupModel lookupModel);

	public List<LookupModel> gearBoxTypeList(LookupModel lookupModel);

	public List<LookupModel> clutchList(LookupModel lookupModel);

	public List<LookupModel> hornList(LookupModel lookupModel);

	public List<LookupModel> mirrorList(LookupModel lookupModel);

	public List<LookupModel> windShieldWiperList(LookupModel lookupModel);

	public List<LookupModel> speedometerList(LookupModel lookupModel);

	public List<LookupModel> steeringList(LookupModel lookupModel);

	public List<LookupModel> drawingList(LookupModel lookupModel);

	/*----------------------------------- End Type Approval/CKD---------------------------*/

	public List<LookupModel> certificateTypeList(LookupModel lookupModel);

	public List<LookupModel> districtList(LookupModel lookupModel);

	public List<LookupModel> examTypeList(LookupModel lookupModel);

	public List<LookupModel> testTypeList(LookupModel lookupModel);

	public List<LookupModel> testResultList(LookupModel lookupModel);

	/*-----------------------------------Start miceleneous---------------------------*/

	public List<LookupModel> concernedDepartmentList(LookupModel lookupModel);

	public List<LookupModel> currentConditionList(LookupModel lookupModel);

	public List<LookupModel> conDeptBranchList(LookupModel lookupModel);

	public List<LookupModel> awardedList(LookupModel lookupModel);

	public List<LookupModel> repairStatusList(LookupModel lookupModel);
	/*-----------------------------------End miceleneous---------------------------*/

	public List<LookupModel> roadTypeList(LookupModel lookupModel);

	/*------------------------------------- Start Report ------------------------------------*/
	public List<LookupModel> licenseClassCodeList(LookupModel lookupModel);
	/*-------------------------------------- End Report ------------------------------------ */
	
	public List<LookupModel> bloodGroupList(LookupModel lookupModel);
	
/*	personalidentification*/
	
	public List<LookupModel> divisionList(LookupModel lookupModel);
	
	public List<LookupModel> pDistrictList(String searchId);
	public List<LookupModel> ThanaList(String searchId);
	public List<LookupModel> JuridictionList(String searchId);
	public List<LookupModel> officeTypeList(LookupModel lookupModel);
	public List<LookupModel> relationShipList(LookupModel lookupModel);
	public List<LookupModel> trainingTypeList(LookupModel lookupModel);
	public List<LookupModel> departmentList(LookupModel lookupModel) ;
	public List<LookupModel> accommodationTypeList(LookupModel lookupModel);
	public List<LookupModel> languageList(LookupModel lookupModel);
	public List<LookupModel> addressType(LookupModel lookupModel);
	public List<LookupModel> bcsBatch(LookupModel lookupModel);
	
	public List<LookupModel> homeType(LookupModel lookupModel);
}
