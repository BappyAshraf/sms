package com.sms.lookUp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.lookUp.dao.LookupDao;
import com.sms.lookUp.model.LookupModel;



@Service("lookupService")
public class LookupServiceImpl implements LookupService {

	@Autowired
	private LookupDao lookupDao;

	public List<LookupModel> branchList(LookupModel lookupModel) {
		return lookupDao.branchList(lookupModel);
	}

	public List<LookupModel> designationList(LookupModel lookupModel) {
		return lookupDao.designationList(lookupModel);
	}

	public List<LookupModel> genderList(LookupModel lookupModel) {
		return lookupDao.genderList(lookupModel);
	}

	public List<LookupModel> relegionList(LookupModel lookupModel) {
		return lookupDao.relegionList(lookupModel);

	}

	public List<LookupModel> maritalStatusList(LookupModel lookupModel) {

		return lookupDao.maritalStatusList(lookupModel);
	}

	public List<LookupModel> nationalityList(LookupModel lookupModel) {

		return lookupDao.nationalityList(lookupModel);
	}

	public List<LookupModel> crntAuthorityList(LookupModel lookupModel) {

		return lookupDao.crntAuthorityList(lookupModel);

	}

	public List<LookupModel> fitnessTypeList(LookupModel lookupModel) {

		return lookupDao.fitnessTypeList(lookupModel);
	}

	public List<LookupModel> currentFuelTypeList(LookupModel lookupModel) {

		return lookupDao.currentFuelTypeList(lookupModel);
	}

	public List<LookupModel> resultList(LookupModel lookupModel) {

		return lookupDao.resultList(lookupModel);

	}

	public List<LookupModel> reportListLoad(String sReportType) {
		return lookupDao.reportListLoad(sReportType);
	}

	public List<LookupModel> vehicleClassList(LookupModel lookupModel) {
		return lookupDao.vehicleClassList(lookupModel);
	}

	public List<LookupModel> routePermitTypeList(LookupModel lookupModel) {
		return lookupDao.routePermitTypeList(lookupModel);
	}

	public List<LookupModel> licenseTypeList(LookupModel lookupModel) {
		return lookupDao.licenseTypeList(lookupModel);
	}

	public List<LookupModel> refusalCodeList(LookupModel lookupModel) {
		return lookupDao.refusalCodeList(lookupModel);
	}

	public List<LookupModel> registrationAreaList(LookupModel lookupModel) {
		return lookupDao.registrationAreaList(lookupModel);
	}

	public List<LookupModel> ownershipTypeList(LookupModel lookupModel) {
		return lookupDao.ownershipTypeList(lookupModel);
	}

	public List<LookupModel> hireList(LookupModel lookupModel) {
		return lookupDao.hireList(lookupModel);
	}

	public List<LookupModel> surrenderTypedList(LookupModel lookupModel) {
		return lookupDao.surrenderTypedList(lookupModel);
	}

	public List<LookupModel> getCertificateIssuedByList(LookupModel lookupModel) {
		return lookupDao.getCertificateIssuedByList(lookupModel);
	}

	public List<LookupModel> acceptanceList(LookupModel lookupModel) {
		return lookupDao.acceptanceList(lookupModel);
	}

	public List<LookupModel> carraigeTypeList(LookupModel lookupModel) {
		return lookupDao.carraigeTypeList(lookupModel);
	}

	public List<LookupModel> timeTableList(LookupModel lookupModel) {
		return lookupDao.timeTableList(lookupModel);
	}

	public List<LookupModel> approvalAuthorityList(LookupModel lookupModel) {
		return lookupDao.approvalAuthorityList(lookupModel);
	}

	public List<LookupModel> cbuSkdCkdList(LookupModel lookupModel) {
		return lookupDao.cbuSkdCkdList(lookupModel);
	}

	public List<LookupModel> applicationTypeList(LookupModel lookupModel) {
		return lookupDao.applicationTypeList(lookupModel);
	}

	public List<LookupModel> examinerList(LookupModel lookupModel) {
		return lookupDao.examinerList(lookupModel);
	}

	public List<LookupModel> licenseClassList(LookupModel lookupModel) {
		return lookupDao.licenseClassList(lookupModel);
	}

	public List<LookupModel> GarageList(LookupModel lookupModel) {
		return lookupDao.GarageList(lookupModel);
	}

	/*----------------------------------- Start Type Approval/CKD---------------------------*/
		
	public List<LookupModel> typeAppIdSearchList(LookupModel lookupModel) {
		return lookupDao.typeAppIdSearchList(lookupModel);
	}
	
	public List<LookupModel> vehicleTypeList(LookupModel lookupModel) {
		return lookupDao.vehicleTypeList(lookupModel);
	}

	public List<LookupModel> companyList(LookupModel lookupModel) {
		return lookupDao.companyList(lookupModel);
	}

	public List<LookupModel> manufacturerList(LookupModel lookupModel) {
		return lookupDao.manufacturerList(lookupModel);
	}

	public List<LookupModel> fuelTypeList(LookupModel lookupModel) {
		return lookupDao.fuelTypeList(lookupModel);
	}

	public List<LookupModel> brakeSystemList(LookupModel lookupModel) {
		return lookupDao.brakeSystemList(lookupModel);
	}

	public List<LookupModel> gearBoxTypeList(LookupModel lookupModel) {
		return lookupDao.gearBoxTypeList(lookupModel);
	}

	public List<LookupModel> clutchList(LookupModel lookupModel) {
		return lookupDao.clutchList(lookupModel);
	}

	public List<LookupModel> hornList(LookupModel lookupModel) {
		return lookupDao.hornList(lookupModel);
	}

	public List<LookupModel> mirrorList(LookupModel lookupModel) {
		return lookupDao.mirrorList(lookupModel);
	}

	public List<LookupModel> windShieldWiperList(LookupModel lookupModel) {
		return lookupDao.windShieldWiperList(lookupModel);
	}

	public List<LookupModel> speedometerList(LookupModel lookupModel) {
		return lookupDao.speedometerList(lookupModel);
	}

	public List<LookupModel> steeringList(LookupModel lookupModel) {
		return lookupDao.steeringList(lookupModel);
	}

	public List<LookupModel> drawingList(LookupModel lookupModel) {
		return lookupDao.drawingList(lookupModel);
	}
	/*----------------------------------- End Type Approval/CKD---------------------------*/

	public List<LookupModel> certificateTypeList(LookupModel lookupModel) {
		return lookupDao.certificateTypeList(lookupModel);
	}

	public List<LookupModel> districtList(LookupModel lookupModel) {
		return lookupDao.districtList(lookupModel);
	}

	public List<LookupModel> examTypeList(LookupModel lookupModel) {
		return lookupDao.examTypeList(lookupModel);
	}

	public List<LookupModel> testTypeList(LookupModel lookupModel) {
		return lookupDao.testTypeList(lookupModel);
	}

	public List<LookupModel> testResultList(LookupModel lookupModel) {
		return lookupDao.testResultList(lookupModel);
	}

	public List<LookupModel> roadTypeList(LookupModel lookupModel) {
		return lookupDao.roadTypeList(lookupModel);
	}

	/*-----------------------------------Start miceleneous---------------------------*/

	public List<LookupModel> concernedDepartmentList(LookupModel lookupModel) {
		return lookupDao.concernedDepartmentList(lookupModel);
	}

	public List<LookupModel> currentConditionList(LookupModel lookupModel) {
		return lookupDao.currentConditionList(lookupModel);
	}

	public List<LookupModel> conDeptBranchList(LookupModel lookupModel) {
		return lookupDao.conDeptBranchList(lookupModel);
	}

	public List<LookupModel> awardedList(LookupModel lookupModel) {
		return lookupDao.awardedList(lookupModel);
	}

	public List<LookupModel> repairStatusList(LookupModel lookupModel) {
		return lookupDao.repairStatusList(lookupModel);
	}

	/*-----------------------------------End miceleneous---------------------------*/

	/*--------------------------------- Start Report ---------------------------------*/

	public List<LookupModel> licenseClassCodeList(LookupModel lookupModel) {
		return lookupDao.licenseClassCodeList(lookupModel);

		/*--------------------------------- End Report ----------------------------- */

	}
	
	public List<LookupModel> bloodGroupList(LookupModel lookupModel){
		return lookupDao.bloodGroupList(lookupModel);
	}
	
	public List<LookupModel> countryList(LookupModel lookupModel){
		return lookupDao.countryList(lookupModel);
	}
	
	
	/* personal Identification*/
	public List<LookupModel> divisionList(LookupModel lookupModel){
		return lookupDao.divisionList(lookupModel);
	}
	
	public List<LookupModel> pDistrictList(String searchId){
		return lookupDao.pDistrictList(searchId);
		
	}
	
	public List<LookupModel> ThanaList(String searchId){
		return lookupDao.ThanaList(searchId);
		
	}
	
	public List<LookupModel> JuridictionList(String searchId){
		return lookupDao.JuridictionList(searchId);
		
	}

	public List<LookupModel> approvalAuthorList(LookupModel lookupModel) {
		return lookupDao.approvalAuthorList(lookupModel);
	}
	
	
	public List<LookupModel> officeTypeList(LookupModel lookupModel){
		return lookupDao.officeTypeList(lookupModel);
		
	}
	
	public List<LookupModel> relationShipList(LookupModel lookupModel){
		return lookupDao.relationShipList(lookupModel);
		
	}
	
	public List<LookupModel> trainingTypeList(LookupModel lookupModel){
		
		return lookupDao.trainingTypeList(lookupModel);
		
	}
	
	public List<LookupModel> departmentList(LookupModel lookupModel) {
		
		return lookupDao.departmentList(lookupModel);
		
	}
	
	public List<LookupModel> accommodationTypeList(LookupModel lookupModel){
		
		return lookupDao.accommodationTypeList(lookupModel);
	}
	
	
	public List<LookupModel> languageList(LookupModel lookupModel){
		
		return lookupDao.languageList(lookupModel);
		
	}
	
	public List<LookupModel> addressType(LookupModel lookupModel){
		
		return lookupDao.addressType(lookupModel);
		
	}
	
	public List<LookupModel> bcsBatch(LookupModel lookupModel){
		return lookupDao.bcsBatch(lookupModel);
		
	}
	
	public List<LookupModel> homeType(LookupModel lookupModel){
		return lookupDao.homeType(lookupModel);
		
	}
/*
	public List<LookupModel> districtList(String searchId){
		
		return lookupDao.districtList(lookupModel);
	}*/
	
	
}
