package services;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import bd.BDException;
import bd.BdTools;
import bd.DBStatic;

public class NotificationLoadService {
	public static JSONObject getNotif(String logKey){
		int id=0;
		try {
			id=BdTools.keyLog2Id(logKey);
		} catch (BDException e) {
			ServicesTools.error("Sqlerror", e.getMessage());
		}
		BasicDBObject masque = new BasicDBObject("cible_id",""+id);
		BasicDBObject sorter = new BasicDBObject("date",-1);

		JSONObject res = new JSONObject();
		try{
			DB db = DBStatic.getMongoDB();
			DBCollection col = db.getCollection("notification");
			DBCursor cursor = col.find(masque).sort(sorter);
			int i = 0;
			while((cursor.hasNext())&&(i<10)){
				DBObject o = cursor.next();
				res.put("Notification_"+ (++i), o);
			}
		}catch (Exception e) {
			return ServicesTools.error("mongo error", "1");
		}
		return res;
	}
}
