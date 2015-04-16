package services;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import bd.BDException;
import bd.BdTools;

public class GetFriendService {
	public static JSONObject getFriend(String logKey){
		JSONObject res=new JSONObject();
		ArrayList<Integer> amis=null;
		try {
			amis =BdTools.getStalked(logKey);
		} catch (BDException e) {
			ServicesTools.error("SqlException", e.getMessage());
		}
		int i=0;
		for(Integer obj : amis){
			i++;
			try {
				res.put("amis_"+i, obj);
			} catch (JSONException e) {
				ServicesTools.error("JSonException", e.getMessage());
			}
		}
		return res;
	}
}
