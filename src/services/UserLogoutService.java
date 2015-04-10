package services;

import org.json.JSONObject;

import bd.BDException;
import bd.BdTools;

public class UserLogoutService {
	public static JSONObject logoutUser(String key){
		try {
			if (BdTools.checkAuthentification(key)){
						BdTools.deleteKey(key);
						return ServicesTools.ok();
					}
					else
						return ServicesTools.error("user not logged", "2");
		} catch (BDException e) {
			return ServicesTools.error("BdError", e.getMessage());
		}
	}
}