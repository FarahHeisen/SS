package services;

import org.json.JSONObject;

import bd.BDException;
import bd.BdTools;

public class UserDeleteService {
	public static JSONObject deleteUser(String login){
		try {
			if( BdTools.userExists(login) ){

				BdTools.deleteUser(login);
				return ServicesTools.ok();
			}
		} catch (BDException e) {
			return ServicesTools.error("error BD", "3");
		}
			
		
		return ServicesTools.error("Unknown user", "2");

	}
}
