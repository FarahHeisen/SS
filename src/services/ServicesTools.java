package services;

import org.json.JSONException;
import org.json.JSONObject;

public class ServicesTools {
	
		public static JSONObject error (String message, String errorCode){
			JSONObject res = new JSONObject();
			try {
				res.put("error", message);
				res.put("error_code", errorCode);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return res;	
		}
		
		public static JSONObject ok () {
			JSONObject res = new JSONObject();
			try {
				res.put("ok", "ok");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		
			return res;	
		}
		
		
		/* renvoie un JSON avec comme paramètre*/
		public static JSONObject ok (String type, String ok) {
			JSONObject res = new JSONObject();
			try {
				res.put(type, ok);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		
			return res;	
		}
	
	
		public static JSONObject JSONLogin (int id,String login, String key) {
			JSONObject res = new JSONObject();
			try {
				res.put("id", id);
				res.put("login", login);
				res.put("key", key);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		
			return res;	
		}
		
		public static JSONObject JSONLogout (int id,String login) {
			JSONObject res = new JSONObject();
			try {
				res.put("id", id);
				res.put("login", login);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		
			return res;	
		}
		
}
