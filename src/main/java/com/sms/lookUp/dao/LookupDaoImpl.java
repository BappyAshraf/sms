package com.sms.lookUp.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aes.protection.CipherUtils;
import com.sms.lookUp.model.LookupModel;

@Repository("lookupDao")
public class LookupDaoImpl implements LookupDao {

	private JdbcTemplate jdbcTemplate;
	CipherUtils oCipherUtils = new CipherUtils();

	@Autowired
	private DataSource dataSource;

	/*
	 * public List<LookupModel> branchList(LookupModel lookupModel) {
	 * jdbcTemplate = new JdbcTemplate(dataSource); ResultSet rs = null;
	 * 
	 * List<LookupModel> oBranchList = new ArrayList<LookupModel>(); try {
	 * Connection conn = jdbcTemplate.getDataSource().getConnection();
	 * StringBuilder sBuilder = new StringBuilder(); sBuilder.append(
	 * "SELECT BRANCH_ID, NAME "); sBuilder.append("FROM L_BRANCH ");
	 * sBuilder.append("WHERE 1 = 1 ");
	 * 
	 * if (lookupModel.getDistrictId() != null) { sBuilder.append(
	 * "AND DISTRICT = ? "); }
	 * 
	 * PreparedStatement prepStmnt = conn.prepareStatement(sBuilder.toString());
	 * if (lookupModel.getDistrictId() != null) { prepStmnt.setString(1,
	 * lookupModel.getDistrictId()); }
	 * 
	 * rs = prepStmnt.executeQuery();
	 * 
	 * while (rs.next()) { LookupModel oLookupModel = new LookupModel();
	 * oLookupModel.setBranchId(rs.getString(1));
	 * oLookupModel.setBranchName(rs.getString(2));
	 * oBranchList.add(oLookupModel); } rs.close(); prepStmnt.close();
	 * conn.close();
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); }
	 * 
	 * return oBranchList; }
	 */

	public List<LookupModel> branchList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);

		List<LookupModel> oBranchList = new ArrayList<LookupModel>();
		try {
			NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("BRANCH_ID,");
			sBuilder.append("NAME ");
			sBuilder.append("FROM L_BRANCH ");
			sBuilder.append("WHERE 1 = 1 ");

			// String sql = "SELECT BRANCH_ID, NAME FROM L_BRANCH " + "WHERE 1 =
			// 1 ";

			MapSqlParameterSource paramSource = new MapSqlParameterSource();

			if (lookupModel.getDistrictId() != null && lookupModel.getDistrictId().length() > 0) {
				sBuilder.append("AND DISTRICT = :districtId ");
				paramSource.addValue("districtId", lookupModel.getDistrictId());
			}
			// System.out.println(sql);

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setBranchId(String.valueOf(row.get("BRANCH_ID")));
				oLookupModel.setBranchName(String.valueOf(row.get("NAME")));
				oBranchList.add(oLookupModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oBranchList;
	}

	public List<LookupModel> designationList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oDesignationList = new ArrayList<LookupModel>();
		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("DESIGNATION_ID,");
			sBuilder.append("DESIGNATION_NAME ");
			sBuilder.append("FROM L_DESIGNATION");

			// String sql = "SELECT DESIGNATION_ID, DESIGNATION_NAME FROM
			// L_DESIGNATION";

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setDesignationId(String.valueOf(row.get("DESIGNATION_ID")));
				oLookupModel.setDesignationName(String.valueOf(row.get("DESIGNATION_NAME")));
				oDesignationList.add(oLookupModel);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return oDesignationList;

	}

	public List<LookupModel> genderList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oGenderList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_ID,");
			sBuilder.append("TYPE_NAME ");
			sBuilder.append("FROM L_GENDER");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setGenderId(String.valueOf(row.get("TYPE_ID")));
				oLookupModel.setGenderName(String.valueOf(row.get("TYPE_NAME")));
				oGenderList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oGenderList;

	}

	public List<LookupModel> relegionList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oRelegionList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("RELIGION_ID,");
			sBuilder.append("RELIGION_NAME ");
			sBuilder.append("FROM L_RELIGION");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setReligionId(String.valueOf(row.get("RELIGION_ID")));
				oLookupModel.setReligionName(String.valueOf(row.get("RELIGION_NAME")));
				oRelegionList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oRelegionList;

	}

	public List<LookupModel> bloodGroupList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oBloodGroupList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("BLOOD_ID,");
			sBuilder.append("BLOOD_GROUP_NAME ");
			sBuilder.append("FROM L_BLOOD_GROUP");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setBloodId(String.valueOf(row.get("BLOOD_ID")));
				oLookupModel.setBloodName(String.valueOf(row.get("BLOOD_GROUP_NAME")));
				oBloodGroupList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oBloodGroupList;

	}

	public List<LookupModel> maritalStatusList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oMaritalStatusList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("MARITAL_ID,");
			sBuilder.append("MARITAL_NAME ");
			sBuilder.append("FROM L_MARITAL_STATUS");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setMaritalId(String.valueOf(row.get("MARITAL_ID")));
				oLookupModel.setMaritalName(String.valueOf(row.get("MARITAL_NAME")));
				oMaritalStatusList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oMaritalStatusList;

	}

	public List<LookupModel> nationalityList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oNationalityList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("NATIONALITY_ID,");
			sBuilder.append("NATIONALITY_NAME ");
			sBuilder.append("FROM L_NATIONALITY");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setNationalityId(String.valueOf(row.get("NATIONALITY_ID")));
				oLookupModel.setNationalityName(String.valueOf(row.get("NATIONALITY_NAME")));
				oNationalityList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oNationalityList;

	}

	public List<LookupModel> countryList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oCountryList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("COUNTRY_ID,");
			sBuilder.append("COUNTRY_NAME ");
			sBuilder.append("FROM L_COUNTRY ");
			sBuilder.append("ORDER BY COUNTRY_NAME ");
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setCountryId(String.valueOf(row.get("COUNTRY_ID")));
				oLookupModel.setCountryName(String.valueOf(row.get("COUNTRY_NAME")));
				oCountryList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oCountryList;

	}

	/* start personal identification */

	public List<LookupModel> divisionList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oDivisionList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("DIVISION_ID,");
			sBuilder.append("DIVISION_NAME ");
			sBuilder.append("FROM L_DIVISION ");
			sBuilder.append("ORDER BY DIVISION_NAME ");
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setDivisionId(String.valueOf(row.get("DIVISION_ID")));
				oLookupModel.setDivisionName(String.valueOf(row.get("DIVISION_NAME")));
				oDivisionList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oDivisionList;
	}

	public List<LookupModel> pDistrictList(String searchId) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<LookupModel> oDistrictList = new ArrayList<LookupModel>();

		try {

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("DISTRICT_ID,");
			sBuilder.append("DISTRICT_NAME ");
			sBuilder.append("FROM L_DISTRICT ");
			sBuilder.append("WHERE DIVISION_ID = :divisionId ");

			MapSqlParameterSource paramSource = new MapSqlParameterSource();

			paramSource.addValue("divisionId", searchId);
			// System.out.println(sBuilder);
			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setDistrictId(String.valueOf(row.get("DISTRICT_ID")));
				oLookupModel.setDistrictName(String.valueOf(row.get("DISTRICT_NAME")));
				oDistrictList.add(oLookupModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oDistrictList;
	}

	public List<LookupModel> JuridictionList(String searchId) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<LookupModel> oDistrictList = new ArrayList<LookupModel>();

		try {

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT BRANCH_ID,NAME FROM L_BRANCH WHERE DIVISION = :divisionId ");
			/*
			 * sBuilder.append(
			 * "(SELECT DIVISION FROM L_BRANCH WHERE BRANCH_ID = :) ");
			 * sBuilder.append("AND BRANCH_ID < 70 ORDER BY NAME ");
			 */

			MapSqlParameterSource paramSource = new MapSqlParameterSource();

			paramSource.addValue("divisionId", searchId);
			// System.out.println(sBuilder);
			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setId(String.valueOf(row.get("BRANCH_ID")));
				oLookupModel.setName(String.valueOf(row.get("NAME")));
				oDistrictList.add(oLookupModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oDistrictList;
	}

	public List<LookupModel> ThanaList(String searchId) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<LookupModel> oThanaList = new ArrayList<LookupModel>();

		try {

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("THANA_ID,");
			sBuilder.append("THANA_NAME ");
			sBuilder.append("FROM L_THANA ");
			sBuilder.append("WHERE BRANCH_ID = :branchId ");

			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("branchId", searchId);
			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setThanaId(String.valueOf(row.get("THANA_ID")));
				oLookupModel.setThanaName(String.valueOf(row.get("THANA_NAME")));
				oThanaList.add(oLookupModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oThanaList;
	}

	/* end */
	/*
	 * public List<LookupModel> thanaList(LookupModel lookupModel) {
	 * jdbcTemplate = new JdbcTemplate(dataSource); List<LookupModel> oThanaList
	 * = new ArrayList<LookupModel>();
	 * 
	 * try { StringBuilder sBuilder = new StringBuilder(); sBuilder.append(
	 * "SELECT "); sBuilder.append("THANA_ID,"); sBuilder.append("THANA_NAME ");
	 * sBuilder.append("FROM L_THANA  "); sBuilder.append("ORDER BY THANA_NAME "
	 * ); List<Map<String, Object>> rows =
	 * jdbcTemplate.queryForList(sBuilder.toString());
	 * 
	 * for (@SuppressWarnings("rawtypes") Map row : rows) { LookupModel
	 * oLookupModel = new LookupModel();
	 * oLookupModel.setThanaId(String.valueOf(row.get("THANA_ID")));
	 * oLookupModel.setThanaName(String.valueOf(row.get("THANA_NAME")));
	 * oThanaList.add(oLookupModel);
	 * 
	 * }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return oThanaList; }
	 */

	/*
	 * public List<LookupModel> crntAuthorityList(LookupModel lookupModel){
	 * 
	 * jdbcTemplate = new JdbcTemplate(dataSource); List<LookupModel>
	 * oCrntAuthorityList = new ArrayList<LookupModel>();
	 * 
	 * try { StringBuilder sBuilder = new StringBuilder(); sBuilder.append(
	 * "SELECT "); sBuilder.append("DISTINCT ");
	 * sBuilder.append("r.CURRENT_AUTHORITY,"); sBuilder.append("l.NAME ");
	 * sBuilder.append("FROM "); sBuilder.append("REGISTRATION r,");
	 * sBuilder.append("L_BRANCH l "); sBuilder.append("WHERE ");
	 * sBuilder.append("R.CURRENT_AUTHORITY = L.BRANCH_ID "); sBuilder.append(
	 * "ORDER BY "); sBuilder.append("CURRENT_AUTHORITY ASC");
	 * 
	 * System.out.println(sBuilder);
	 * 
	 * 
	 * List<Map<String,Object>> rows =
	 * jdbcTemplate.queryForList(sBuilder.toString());
	 * 
	 * System.out.println("000000000000");
	 * 
	 * for (Map row : rows ){ System.out.println("111111111111"); LookupModel
	 * oLookupModel = new LookupModel();
	 * oLookupModel.setCurntAuthority(String.valueOf(row.get("CURRENT_AUTHORITY"
	 * ))); oLookupModel.setBranchName(String.valueOf(row.get("NAME")));
	 * oCrntAuthorityList.add(oLookupModel);
	 * 
	 * }
	 * 
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); } return oCrntAuthorityList;
	 * 
	 * }
	 */

	public List<LookupModel> crntAuthorityList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oCrntAuthList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();

			sBuilder.append("SELECT ");
			sBuilder.append("BRANCH_ID,");
			sBuilder.append("NAME ");
			sBuilder.append("FROM l_branch");

			// System.out.println(sBuilder);

			// sBuilder.append("select BRANCH_ID, NAME from l_branch l ");
			// sBuilder.append("WHERE L.BRANCH_ID IN (13,17)");

			// System.out.println(sBuilder.toString());

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			// System.out.println("1 "+rows.indexOf(1));
			// System.out.println("2 "+rows.indexOf("BRANCH_ID"));

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				// System.out.println("111111111111");
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setBranchId(String.valueOf(row.get("BRANCH_ID")));
				oLookupModel.setBranchName(String.valueOf(row.get("NAME")));
				oCrntAuthList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(oCrntAuthList.size());

		return oCrntAuthList;

	}

	public List<LookupModel> fitnessTypeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> ofitnessTypeList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_ID,");
			sBuilder.append("TYPE_NAME ");
			sBuilder.append("FROM L_FITNESS_TYPE");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setTypeId(String.valueOf(row.get("TYPE_ID")));
				oLookupModel.setTypeName(String.valueOf(row.get("TYPE_NAME")));
				ofitnessTypeList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ofitnessTypeList;
	}

	public List<LookupModel> currentFuelTypeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> ocurrentFuelTypeList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_ID,");
			sBuilder.append("TYPE_DESCRIPTION ");
			sBuilder.append("FROM L_FUEL_TYPE");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setTypeId(String.valueOf(row.get("TYPE_ID")));
				oLookupModel.setTypeDescription(String.valueOf(row.get("TYPE_DESCRIPTION")));
				ocurrentFuelTypeList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ocurrentFuelTypeList;
	}

	public List<LookupModel> resultList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oresultList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("RESULT_ID,");
			sBuilder.append("RESULT_NAME ");
			sBuilder.append("FROM L_INSPECTION_RESULT");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setResultId(String.valueOf(row.get("RESULT_ID")));
				oLookupModel.setResultName(String.valueOf(row.get("RESULT_NAME")));
				oresultList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oresultList;
	}

	public List<LookupModel> reportListLoad(String sReportType) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<LookupModel> oReportList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT REPORT_CODE, REPORT_NAME ");
			sBuilder.append("FROM REPORT_LIST ");
			sBuilder.append("WHERE REPORT_TYPE = :reportType AND  SUB_REPORT_TYPE IS NULL ");
			sBuilder.append("ORDER BY REPORT_ID");

			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("reportType", sReportType);

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setReportCode(String.valueOf(row.get("REPORT_CODE")));
				oLookupModel.setReportName(String.valueOf(row.get("REPORT_NAME")));
				oReportList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oReportList;
	}

	public List<LookupModel> vehicleClassList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> ovehicleClassList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("CLASS_ID,");
			sBuilder.append("CLASS_NAME ");
			sBuilder.append("FROM L_VEHICLE_CLASS");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setClassId(String.valueOf(row.get("CLASS_ID")));
				oLookupModel.setClassName(String.valueOf(row.get("CLASS_NAME")));
				ovehicleClassList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ovehicleClassList;
	}

	public List<LookupModel> routePermitTypeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oroutePermitTypeList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_ID,");
			sBuilder.append("TYPE_NAME ");
			sBuilder.append("FROM L_ROUTE_PERMITS_TYPE");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setTypeId(String.valueOf(row.get("TYPE_ID")));
				oLookupModel.setTypeName(String.valueOf(row.get("TYPE_NAME")));
				oroutePermitTypeList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return oroutePermitTypeList;
	}

	public List<LookupModel> licenseTypeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> olicenseTypeList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_ID,");
			sBuilder.append("TYPE_NAME ");
			sBuilder.append("FROM L_LICENSE_TYPE");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setTypeId(String.valueOf(row.get("TYPE_ID")));
				oLookupModel.setTypeName(String.valueOf(row.get("TYPE_NAME")));
				olicenseTypeList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return olicenseTypeList;
	}

	public List<LookupModel> refusalCodeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> refusalCodeList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("REFUSAL_ID,");
			sBuilder.append("REFUSAL_NAME ");
			sBuilder.append("FROM L_REFUSAL_CODE");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setRefusalId(String.valueOf(row.get("REFUSAL_ID")));
				oLookupModel.setRefusalName(String.valueOf(row.get("REFUSAL_NAME")));
				refusalCodeList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return refusalCodeList;
	}

	public List<LookupModel> registrationAreaList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<LookupModel> registrationAreaList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT DISTINCT LD.DISTRICT_ID, LD.DISTRICT_NAME ");
			sBuilder.append("FROM L_DISTRICT LD, REGISTRATION_NO_GENERATION RG ");
			sBuilder.append("WHERE LD.DISTRICT_ID = RG.DISTRICT_ID ");
			sBuilder.append("AND RG.BRANCH_ID = :branchCode");

			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("branchCode", lookupModel.getRegistaringArea());

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setAreaId(String.valueOf(row.get("DISTRICT_ID")));
				oLookupModel.setAreaName(String.valueOf(row.get("DISTRICT_NAME")));
				registrationAreaList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return registrationAreaList;
	}

	public List<LookupModel> approvalAuthorList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<LookupModel> approvalAuthorList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append(" SELECT distinct WP.PATH_ID,");
			sBuilder.append(" WP.PATH_NAME ");
			sBuilder.append(" FROM  WORKFLOW_RECIPIENT WR,WORKFLOW_PATH WP ");
			sBuilder.append(" WHERE WR.PATH_ID=WP.PATH_ID");
			sBuilder.append(" AND WR.BRANCH_ID='2' ");
			sBuilder.append(" AND WR.RULE_ID='R-10701'");

			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("branchCode", lookupModel.getRegistaringArea());
			paramSource.addValue("id", lookupModel.getId());

			List<Map<String, Object>> rows = npjt.queryForList(sBuilder.toString(), paramSource);

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setAreaId(String.valueOf(row.get("PATH_ID")));
				oLookupModel.setAreaName(String.valueOf(row.get("PATH_NAME")));
				approvalAuthorList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return approvalAuthorList;
	}

	public List<LookupModel> ownershipTypeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> ownershipTypeList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_ID,");
			sBuilder.append("TYPE_NAME ");
			sBuilder.append("FROM L_OWNERSHIP_TYPE");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setOwnershipTypeId(String.valueOf(row.get("TYPE_ID")));
				oLookupModel.setOwnershipTypeName(String.valueOf(row.get("TYPE_NAME")));
				ownershipTypeList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ownershipTypeList;
	}

	public List<LookupModel> hireList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> hireList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("HIRE_ID,");
			sBuilder.append("HIRE_NAME ");
			sBuilder.append("FROM L_HIRE");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setHireId(String.valueOf(row.get("HIRE_ID")));
				oLookupModel.setHireName(String.valueOf(row.get("HIRE_NAME")));
				hireList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return hireList;
	}

	public List<LookupModel> surrenderTypedList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> surrenderTypedList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("SURRENDER_TYPE_ID,");
			sBuilder.append("TYPE_NAME ");
			sBuilder.append("FROM L_SURRENDER_TYPE");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setSurrenderTypedId(String.valueOf(row.get("SURRENDER_TYPE_ID")));
				oLookupModel.setSurrenderTypeName(String.valueOf(row.get("TYPE_NAME")));
				surrenderTypedList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return surrenderTypedList;
	}

	public List<LookupModel> getCertificateIssuedByList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> getCertificateIssuedByList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append(
					"DISTINCT E.EMPLOYEE_ID, E.EMPLOYEE_ID||' - '||E.FIRST_NAME FIRST_NAME, D.DESIGNATION_NAME ");
			sBuilder.append("FROM EMPLOYEE E, JOB_TRACK J, L_DESIGNATION D ");
			sBuilder.append("WHERE E.EMPLOYEE_ID = J.EMPLOYEE_ID ");
			sBuilder.append("AND J.DESIGNATION_ID = D.DESIGNATION_ID ");
			sBuilder.append("AND J.JOB_SEQUENCE_ID = (SELECT  MAX (JOB_SEQUENCE_ID) ");
			sBuilder.append("FROM  JOB_TRACK ");
			sBuilder.append("WHERE  EMPLOYEE_ID = E.EMPLOYEE_ID) ");
			sBuilder.append("ORDER BY  FIRST_NAME ");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setEmployeeId(String.valueOf(row.get("EMPLOYEE_ID")));
				oLookupModel.setEmployeeName(String.valueOf(row.get("FIRST_NAME")));
				oLookupModel.setEmployeeDesignation(String.valueOf(row.get("DESIGNATION_NAME")));
				getCertificateIssuedByList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return getCertificateIssuedByList;
	}

	public List<LookupModel> acceptanceList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> acceptanceTypedList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_ID,");
			sBuilder.append("TYPE_DESCRIPTION ");
			sBuilder.append("FROM L_ACCEPTANCE");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setTypeId(String.valueOf(row.get("TYPE_ID")));
				oLookupModel.setTypeDescription(String.valueOf(row.get("TYPE_DESCRIPTION")));
				acceptanceTypedList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return acceptanceTypedList;
	}

	public List<LookupModel> carraigeTypeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> carraigeTypedList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_ID,");
			sBuilder.append("TYPE_DESCRIPTION ");
			sBuilder.append("FROM L_CARRIAGE_TYPE");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setTypeId(String.valueOf(row.get("TYPE_ID")));
				oLookupModel.setTypeDescription(String.valueOf(row.get("TYPE_DESCRIPTION")));
				carraigeTypedList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return carraigeTypedList;
	}

	public List<LookupModel> timeTableList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> timeTableTypedList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TT_ID,");
			sBuilder.append("TT_DESCRIPTION ");
			sBuilder.append("FROM L_TT_ATTACHED");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setTypeId(String.valueOf(row.get("TT_ID")));
				oLookupModel.setTypeDescription(String.valueOf(row.get("TT_DESCRIPTION")));
				timeTableTypedList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return timeTableTypedList;
	}

	public List<LookupModel> approvalAuthorityList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> approvalAuthorityTypedList = new ArrayList<LookupModel>();

		/*
		 * try { StringBuilder sBuilder = new StringBuilder(); sBuilder.append(
		 * "SELECT "); sBuilder.append("TYPE_ID,"); sBuilder.append(
		 * "TYPE_DESCRIPTION "); sBuilder.append("FROM L_CARRIAGE_TYPE");
		 * 
		 * 
		 * List<Map<String,Object>> rows =
		 * jdbcTemplate.queryForList(sBuilder.toString());
		 * 
		 * for(@SuppressWarnings("rawtypes") Map row : rows ){ LookupModel
		 * oLookupModel = new LookupModel();
		 * oLookupModel.setTypeId(String.valueOf(row.get("TYPE_ID")));
		 * oLookupModel.setTypeDescription(String.valueOf(row.get(
		 * "TYPE_DESCRIPTION"))); approvalAuthorityTypedList.add(oLookupModel);
		 * 
		 * }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

		return approvalAuthorityTypedList;
	}

	public List<LookupModel> cbuSkdCkdList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> getCbuSkdCkdList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("CBU_SKD_CKD_ID, TYPE_NAME ");
			sBuilder.append("FROM  L_CBU_SKD_CKD ");
			sBuilder.append("ORDER BY  TYPE_NAME ");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setCbuSkdCkdId(String.valueOf(row.get("CBU_SKD_CKD_ID")));
				oLookupModel.setCbuSkdCkdTypeName(String.valueOf(row.get("TYPE_NAME")));
				getCbuSkdCkdList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return getCbuSkdCkdList;
	}

	public List<LookupModel> applicationTypeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> applicationTypeList = new ArrayList<LookupModel>();
		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_ID,");
			sBuilder.append("TYPE_DESCRIPTION ");
			sBuilder.append("FROM L_APPLICATION_TYPE");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setAppTypeId(String.valueOf(row.get("TYPE_ID")));
				oLookupModel.setAppTypeName(String.valueOf(row.get("TYPE_DESCRIPTION")));
				applicationTypeList.add(oLookupModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return applicationTypeList;
	}

	public List<LookupModel> examinerList(LookupModel lookupModel) {
		/*
		 * jdbcTemplate = new JdbcTemplate(dataSource); List<LookupModel>
		 * examinerList = new ArrayList<LookupModel>(); try { StringBuilder
		 * sBuilder = new StringBuilder(); sBuilder.append("SELECT ");
		 * sBuilder.append("LICENSE_ID,"); sBuilder.append("EXAMINER ");
		 * sBuilder.append("FROM LICENSE");
		 * 
		 * List<Map<String, Object>> rows =
		 * jdbcTemplate.queryForList(sBuilder.toString());
		 * 
		 * for (@SuppressWarnings("rawtypes") Map row : rows) { LookupModel
		 * oLookupModel = new LookupModel();
		 * oLookupModel.setLicenseId(String.valueOf(row.get("LICENSE_ID")));
		 * oLookupModel.setExaminer(String.valueOf(row.get("EXAMINER")));
		 * examinerList.add(oLookupModel); } } catch (Exception e) {
		 * e.printStackTrace(); }
		 */

		return null;
	}

	public List<LookupModel> licenseClassList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> licenseClassList = new ArrayList<LookupModel>();
		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("LICENSE_CLASS_ID,");
			sBuilder.append("LICENSE_CLASS_NAME ");
			sBuilder.append("FROM L_LICENSE_CLASS");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setLicenseClassId(String.valueOf(row.get("LICENSE_CLASS_ID")));
				oLookupModel.setLicenseClassName(String.valueOf(row.get("LICENSE_CLASS_NAME")));
				licenseClassList.add(oLookupModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return licenseClassList;
	}

	public List<LookupModel> GarageList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> getGarageList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("GARAGE_TYPE_ID, GARAGE_TYPE_NAME ");
			sBuilder.append("FROM  L_GARAGE_TYPE");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setTypeId(String.valueOf(row.get("GARAGE_TYPE_ID")));
				oLookupModel.setTypeName(String.valueOf(row.get("GARAGE_TYPE_NAME")));
				getGarageList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return getGarageList;
	}

	/*----------------------------------- Start Type Approval/CKD---------------------------*/

	public List<LookupModel> typeAppIdSearchList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> ovehicleTypeList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_APPROVAL_ID,");
			sBuilder.append("TYPE_APPROVAL_NAME ");
			sBuilder.append("FROM VEW_TYPE_APPROVAL_DETAILS ");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setTypeApprovalId(String.valueOf(row.get("TYPE_APPROVAL_ID")));
				oLookupModel.setTypeApprovalName(String.valueOf(row.get("TYPE_APPROVAL_NAME")));
				ovehicleTypeList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ovehicleTypeList;
	}

	public List<LookupModel> vehicleTypeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> ovehicleTypeList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_ID,");
			sBuilder.append("TYPE_NAME ");
			sBuilder.append("FROM L_VEHICLE_TYPE");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setTypeId(String.valueOf(row.get("TYPE_ID")));
				oLookupModel.setTypeName(String.valueOf(row.get("TYPE_NAME")));
				ovehicleTypeList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ovehicleTypeList;
	}

	public List<LookupModel> companyList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> getCompanyList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("COMPANY_ID, NAME ");
			sBuilder.append("FROM  L_COMPANY ");
			// sBuilder.append("ORDER BY NAME ");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setCompanyId(String.valueOf(row.get("COMPANY_ID")));
				oLookupModel.setCompanyName(String.valueOf(row.get("NAME")));
				getCompanyList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return getCompanyList;
	}

	public List<LookupModel> manufacturerList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> getmanufacturerList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("MANUFACTURER_ID, ");
			sBuilder.append("MANUFACTURER_NAME ");
			sBuilder.append("FROM  L_MANUFACTURER ");
			// sBuilder.append("ORDER BY NAME ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setManufacturerId(String.valueOf(row.get("MANUFACTURER_ID")));
				oLookupModel.setManufacturerName(String.valueOf(row.get("MANUFACTURER_NAME")));
				getmanufacturerList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return getmanufacturerList;
	}

	public List<LookupModel> fuelTypeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> fuelTypeList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_ID, ");
			sBuilder.append("TYPE_DESCRIPTION ");
			sBuilder.append("FROM  L_FUEL_TYPE ");
			// sBuilder.append("ORDER BY NAME ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setFuelTypeId(String.valueOf(row.get("TYPE_ID")));
				oLookupModel.setFuelTypeName(String.valueOf(row.get("TYPE_DESCRIPTION")));
				fuelTypeList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return fuelTypeList;
	}

	public List<LookupModel> brakeSystemList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> brakeSystemList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("BRAKE_ID, ");
			sBuilder.append("BRAKE_NAME ");
			sBuilder.append("FROM  L_BRAKE_SYSTEM ");
			// sBuilder.append("ORDER BY NAME ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setBrakeId(String.valueOf(row.get("BRAKE_ID")));
				oLookupModel.setBrakeName(String.valueOf(row.get("BRAKE_NAME")));
				brakeSystemList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return brakeSystemList;
	}

	public List<LookupModel> gearBoxTypeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> gearBoxTypeList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("GEAR_ID, ");
			sBuilder.append("GEAR_NAME ");
			sBuilder.append("FROM  L_GEAR_TYPE ");
			// sBuilder.append("ORDER BY NAME ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setGearId(String.valueOf(row.get("GEAR_ID")));
				oLookupModel.setGearName(String.valueOf(row.get("GEAR_NAME")));
				gearBoxTypeList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return gearBoxTypeList;
	}

	public List<LookupModel> clutchList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> clutchList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("CLUTCH_ID, ");
			sBuilder.append("CLUTCH_NAME ");
			sBuilder.append("FROM  L_CLUTCH ");
			// sBuilder.append("ORDER BY NAME ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setClutchId(String.valueOf(row.get("CLUTCH_ID")));
				oLookupModel.setClutchName(String.valueOf(row.get("CLUTCH_NAME")));
				clutchList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return clutchList;
	}

	public List<LookupModel> hornList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> hornList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("HORN_ID, ");
			sBuilder.append("HORN_NAME ");
			sBuilder.append("FROM  L_HORN ");
			// sBuilder.append("ORDER BY NAME ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setHornId(String.valueOf(row.get("HORN_ID")));
				oLookupModel.setHornName(String.valueOf(row.get("HORN_NAME")));
				hornList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return hornList;
	}

	public List<LookupModel> mirrorList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> mirrorList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("MIRROR_ID, ");
			sBuilder.append("MIRROR_NAME ");
			sBuilder.append("FROM  L_MIRROR ");
			// sBuilder.append("ORDER BY NAME ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setMirrorId(String.valueOf(row.get("MIRROR_ID")));
				oLookupModel.setMirrorName(String.valueOf(row.get("MIRROR_NAME")));
				mirrorList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mirrorList;
	}

	public List<LookupModel> windShieldWiperList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> windShieldWiperList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("WIPER_ID, ");
			sBuilder.append("WIPER_NAME ");
			sBuilder.append("FROM  L_WINDOW_WIPER ");
			// sBuilder.append("ORDER BY NAME ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setWiperId(String.valueOf(row.get("WIPER_ID")));
				oLookupModel.setWiperName(String.valueOf(row.get("WIPER_NAME")));
				windShieldWiperList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return windShieldWiperList;
	}

	public List<LookupModel> speedometerList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> speedometerList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("SPEEDOMETER_ID, ");
			sBuilder.append("SPEEDOMETER_NAME ");
			sBuilder.append("FROM  L_SPEEDOMETER ");
			// sBuilder.append("ORDER BY NAME ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setSpeedometerId(String.valueOf(row.get("SPEEDOMETER_ID")));
				oLookupModel.setSpeedometerName(String.valueOf(row.get("SPEEDOMETER_NAME")));
				speedometerList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return speedometerList;
	}

	public List<LookupModel> steeringList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> steeringList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("STEERING_ID, ");
			sBuilder.append("STEERING_NAME ");
			sBuilder.append("FROM  L_STEERING ");
			// sBuilder.append("ORDER BY NAME ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setSteeringId(String.valueOf(row.get("STEERING_ID")));
				oLookupModel.setSteeringName(String.valueOf(row.get("STEERING_NAME")));
				steeringList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return steeringList;
	}

	public List<LookupModel> drawingList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> drawingList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("DRAWING_ID, ");
			sBuilder.append("DRAWING_NAME ");
			sBuilder.append("FROM  L_DRAWING ");
			// sBuilder.append("ORDER BY NAME ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setDrawingId(String.valueOf(row.get("DRAWING_ID")));
				oLookupModel.setDrawingName(String.valueOf(row.get("DRAWING_NAME")));
				drawingList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return drawingList;
	}

	/*----------------------------------- End Type Approval/CKD---------------------------*/

	public List<LookupModel> roadTypeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> roadTypeList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_ID,");
			sBuilder.append("TYPE_NAME ");
			sBuilder.append("FROM L_ROAD_TYPE ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setTypeId(String.valueOf(row.get("TYPE_ID")));
				oLookupModel.setTypeName(String.valueOf(row.get("TYPE_NAME")));
				roadTypeList.add(oLookupModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roadTypeList;
	}

	public List<LookupModel> certificateTypeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> certificateTypeList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT REPORT_CODE, ");
			sBuilder.append("REPORT_NAME ");
			sBuilder.append("FROM REPORT_LIST ");
			sBuilder.append("WHERE REPORT_ID IN(147,148,149,150,151,152,153,154,155) ");
			sBuilder.append("ORDER BY REPORT_ID ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setReportCode(String.valueOf(row.get("REPORT_CODE")));
				oLookupModel.setReportName(String.valueOf(row.get("REPORT_NAME")));
				certificateTypeList.add(oLookupModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return certificateTypeList;
	}

	public List<LookupModel> districtList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> districtList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("DISTRICT_ID, ");
			sBuilder.append("DISTRICT_NAME ");
			sBuilder.append("FROM  L_DISTRICT ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setDistrictId(String.valueOf(row.get("DISTRICT_ID")));
				oLookupModel.setDistrictName(String.valueOf(row.get("DISTRICT_NAME")));
				districtList.add(oLookupModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return districtList;
	}

	public List<LookupModel> examTypeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> examTypeList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_ID,");
			sBuilder.append("TYPE_DESCRIPTION ");
			sBuilder.append("FROM L_EXAM_TYPE ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setTypeId(String.valueOf(row.get("TYPE_ID")));
				oLookupModel.setTypeDescription(String.valueOf(row.get("TYPE_DESCRIPTION")));
				examTypeList.add(oLookupModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return examTypeList;
	}

	public List<LookupModel> testTypeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> testTypeList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TEST_ID,");
			sBuilder.append("TEST_NAME ");
			sBuilder.append("FROM L_TEST_TYPE ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setTestId(String.valueOf(row.get("TEST_ID")));
				oLookupModel.setTestName(String.valueOf(row.get("TEST_NAME")));
				testTypeList.add(oLookupModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testTypeList;
	}

	public List<LookupModel> testResultList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> testResultList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append(" RESULT_ID,");
			sBuilder.append("RESULT_DESCRIPTION ");
			sBuilder.append("FROM L_RESULT ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setResultId(String.valueOf(row.get("RESULT_ID")));
				oLookupModel.setResultName(String.valueOf(row.get("RESULT_DESCRIPTION")));
				testResultList.add(oLookupModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testResultList;
	}

	/*-----------------------------------start miceleneous---------------------------*/

	public List<LookupModel> concernedDepartmentList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> concernedDepartmentList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append(" CONCERN_ID,");
			sBuilder.append("CONCERN_NAME ");
			sBuilder.append("FROM L_CONCERNED_DEPART ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setConcernId(String.valueOf(row.get("CONCERN_ID")));
				oLookupModel.setConcernName(String.valueOf(row.get("CONCERN_NAME")));
				concernedDepartmentList.add(oLookupModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return concernedDepartmentList;
	}

	public List<LookupModel> currentConditionList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> currentConditionList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append(" CONDITION_ID,");
			sBuilder.append("CONDITION_NAME ");
			sBuilder.append("FROM L_VEHICLE_CONDITION ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setConditionId(String.valueOf(row.get("CONDITION_ID")));
				oLookupModel.setConditionName(String.valueOf(row.get("CONDITION_NAME")));
				currentConditionList.add(oLookupModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentConditionList;
	}

	public List<LookupModel> conDeptBranchList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> conDeptBranchList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append(" BRANCH_ID,");
			sBuilder.append("BRANCH_NAME ");
			sBuilder.append("FROM L_CONCERNED_DEPART_BRANCH ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setBranchId(String.valueOf(row.get("BRANCH_ID")));
				oLookupModel.setBranchName(String.valueOf(row.get("BRANCH_NAME")));
				conDeptBranchList.add(oLookupModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conDeptBranchList;
	}

	public List<LookupModel> awardedList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> awardedList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append(" AWARDED_ID,");
			sBuilder.append("AWARDED_NAME ");
			sBuilder.append("FROM L_AWARDED ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setAwardedId(String.valueOf(row.get("AWARDED_ID")));
				oLookupModel.setAwardedName(String.valueOf(row.get("AWARDED_NAME")));
				awardedList.add(oLookupModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return awardedList;
	}

	public List<LookupModel> repairStatusList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> repairStatusList = new ArrayList<LookupModel>();

		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append(" STATUS_ID,");
			sBuilder.append("STATUS_DESCRIPTION ");
			sBuilder.append("FROM L_REPAIR_STATUS ");

			// System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setStatusId(String.valueOf(row.get("STATUS_ID")));
				oLookupModel.setStatusName(String.valueOf(row.get("STATUS_DESCRIPTION")));
				repairStatusList.add(oLookupModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return repairStatusList;
	}

	/*-----------------------------------End miceleneous---------------------------*/

	public List<LookupModel> licenseClassCodeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> licenseClassCodeList = new ArrayList<LookupModel>();
		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("LICENSE_ID,");
			sBuilder.append("LICENSE_CLASS_CODE ");
			sBuilder.append("FROM LICENSE_CLASS");

			System.out.println(sBuilder);

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setLicenseId(String.valueOf(row.get("LICENSE_ID")));
				oLookupModel.setLicenseClassCode(String.valueOf(row.get("LICENSE_CLASS_CODE")));
				licenseClassCodeList.add(oLookupModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return licenseClassCodeList;
	}
	
/*	lija */
	public List<LookupModel> officeTypeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oOfficeTypeList = new ArrayList<LookupModel>();
		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_ID,");
			sBuilder.append("TYPE_NAME ");
			sBuilder.append("FROM L_OFFICE_TYPE");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setOfcTypeId(String.valueOf(row.get("TYPE_ID")));
				oLookupModel.setOfcTypeName(String.valueOf(row.get("TYPE_NAME")));
				oOfficeTypeList.add(oLookupModel);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return oOfficeTypeList;

	}
	
	
	public List<LookupModel> relationShipList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oRelationShipList = new ArrayList<LookupModel>();
		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_ID,");
			sBuilder.append("TYPE_NAME ");
			sBuilder.append("FROM L_RELATIONSHIP");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setRelationshipId(String.valueOf(row.get("TYPE_ID")));
				oLookupModel.setRelationshipName(String.valueOf(row.get("TYPE_NAME")));
				oRelationShipList.add(oLookupModel);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return oRelationShipList;

	}
	
	public List<LookupModel> trainingTypeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oTrainingTypeList = new ArrayList<LookupModel>();
		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_ID,");
			sBuilder.append("TYPE_NAME ");
			sBuilder.append("FROM L_TRAINING_TYPE");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setTrainingId(String.valueOf(row.get("TYPE_ID")));
				oLookupModel.setTrainingName(String.valueOf(row.get("TYPE_NAME")));
				oTrainingTypeList.add(oLookupModel);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return oTrainingTypeList;

	}
	
	public List<LookupModel> departmentList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oDepartmentList = new ArrayList<LookupModel>();
		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("DEPARTMENT_ID,");
			sBuilder.append("DEPARTMENT_NAME ");
			sBuilder.append("FROM L_DEPARTMENT");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setDepartmentId(String.valueOf(row.get("DEPARTMENT_ID")));
				oLookupModel.setDepartmentName(String.valueOf(row.get("DEPARTMENT_NAME")));
				oDepartmentList.add(oLookupModel);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return oDepartmentList;

	}
	
	public List<LookupModel> accommodationTypeList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oAccommodationType = new ArrayList<LookupModel>();
		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_ID,");
			sBuilder.append("TYPE_NAME ");
			sBuilder.append("FROM L_ACCOMMODATION_TYPE");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setTypeId(String.valueOf(row.get("TYPE_ID")));
				oLookupModel.setTypeName(String.valueOf(row.get("TYPE_NAME")));
				oAccommodationType.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return oAccommodationType;
	}
	
	
	public List<LookupModel> languageList(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oLanguageList = new ArrayList<LookupModel>();
		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("LANGUAGE_ID,");
			sBuilder.append("LANGUAGE_NAME ");
			sBuilder.append("FROM L_LANGUAGE");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setLanguageId(String.valueOf(row.get("LANGUAGE_ID")));
				oLookupModel.setLanguageName(String.valueOf(row.get("LANGUAGE_NAME")));
				oLanguageList.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return oLanguageList;
	}
	
	public List<LookupModel> addressType(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oAddressType = new ArrayList<LookupModel>();
		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_ID,");
			sBuilder.append("TYPE_NAME ");
			sBuilder.append("FROM L_ADDRESS_TYPE");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setTypeId(String.valueOf(row.get("TYPE_ID")));
				oLookupModel.setTypeName(String.valueOf(row.get("TYPE_NAME")));
				oAddressType.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return oAddressType;
	}
	
	public List<LookupModel> bcsBatch(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oBcsBatch = new ArrayList<LookupModel>();
		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("BATCH_ID,");
			sBuilder.append("BATCH_NAME ");
			sBuilder.append("FROM L_BCS_BATCH");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setBatchId(String.valueOf(row.get("BATCH_ID")));
				oLookupModel.setBatchName(String.valueOf(row.get("BATCH_NAME")));
				oBcsBatch.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return oBcsBatch;
	}
	
	
	public List<LookupModel> homeType(LookupModel lookupModel) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<LookupModel> oHomeType = new ArrayList<LookupModel>();
		try {
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT ");
			sBuilder.append("TYPE_ID,");
			sBuilder.append("TYPE_NAME ");
			sBuilder.append("FROM L_HOME_TYPE");

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sBuilder.toString());

			for (@SuppressWarnings("rawtypes")
			Map row : rows) {
				LookupModel oLookupModel = new LookupModel();
				oLookupModel.setTypeId(String.valueOf(row.get("TYPE_ID")));
				oLookupModel.setTypeName(String.valueOf(row.get("TYPE_NAME")));
				oHomeType.add(oLookupModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return oHomeType;
	}
	
	
	
	
	
	
	

}
