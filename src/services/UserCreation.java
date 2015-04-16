package services;

import org.json.JSONObject;

import bd.BDException;
import bd.BdTools;

public class UserCreation {

	public static JSONObject createUser(String login, String pwd, String prenom, String nom) {
		try {
			if(!BdTools.userExists(login)){
				BdTools.createUser(login, pwd, prenom, nom);
				return ServicesTools.ok("login", login); // modifié 
			}
			
		} catch (BDException e) {
			return ServicesTools.error("error", "user creation "+ e.getMessage());
			
		}
		return ServicesTools.error("user exists", "1");
		
	}

}
