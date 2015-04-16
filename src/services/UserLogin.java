package services;

import org.json.JSONObject;

import bd.BDException;
import bd.BdTools;

public class UserLogin {

	/**
	 * 
	 * @return une clef aléatoire de 32 caractere
	 */
	private static String generateKey() {
		String s = "";
		char tab[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		for (int i = 0; i < 32; i++)
			s += tab[(int) (Math.random() * tab.length)];
		return s;
	}


	public static JSONObject authentifiateUser(String login, String password){
		try {
			int id=0;
			if (BdTools.userExists(login)){
				if (BdTools.checkPassword(login, password) ){
					id = BdTools.returnId(login);
					if (!BdTools.checkConnection(login)){
						BdTools.logoutId(id);
					}
					String key = generateKey();
					BdTools.addKey( id, key);
					return ServicesTools.JSONLogin(id, login, key);
				}
				else{
					return ServicesTools.error("incorrect_password", "2");
				}
				
			}

		} catch (BDException e) {
			return ServicesTools.error("BdError", e.getMessage());
		}

		return ServicesTools.error("user_don't_exist", "1");
	}

}
