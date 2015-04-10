package services;

import org.json.JSONObject;

import bd.BDException;
import bd.BdTools;
import bd.MongoTools;

public class AddFollowerService {
	public static JSONObject addFollower(String my_key, int his_id){
		int myId;
		try {
			myId = BdTools.keyLog2Id(my_key);
			if(BdTools.checkAuthentification(my_key)){
				BdTools.addFollower(myId, his_id);
			}else{
				return ServicesTools.error("authatification : ", "you aren't logged");
			}
		} catch (BDException e) {
			return ServicesTools.error("BdException : ", e.getMessage());
		}
		MongoTools.notify(his_id, "follow", myId);
		return ServicesTools.ok();
	}
}
