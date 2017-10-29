package com.sms.login.service;

import java.util.List;

import com.sms.login.model.MenuInfo;
import com.sms.login.model.LoginInfo;

public interface LoginService {

	public LoginInfo ValidateUser(String sUserName, String sUserPass, String sTerminalIp);

	public List<MenuInfo> GetPermittedMenuInfo(String UserId, String ParentMenuID);

	public MenuInfo checkUserAuthorization(String UserId, String MenuID);
}
