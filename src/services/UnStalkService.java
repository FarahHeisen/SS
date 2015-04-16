package services;

import org.json.JSONObject;

import bd.BdTools;

public class UnStalkService {
	public static JSONObject unfollow(String Key, int his_id){
		try{
		if(BdTools.checkAuthentification(Key)){
			int my_id = BdTools.keyLog2Id(Key);
			BdTools.removeFollower(my_id, his_id);
		}
		}catch(Exception e){
			return ServicesTools.error("unStalk Error", "1");
		}
		return ServicesTools.ok();
	}
}
