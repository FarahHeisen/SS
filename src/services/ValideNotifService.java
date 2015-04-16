package services;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;
import org.json.JSONObject;

import bd.DBStatic;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoException;

public class ValideNotifService {

	public static JSONObject validate(String notifId){
		DB db=null;
		try {
			db = DBStatic.getMongoDB();
		} catch (UnknownHostException | MongoException e) {
			return ServicesTools.error("MongoDB error", e.getMessage());
		}
		DBCollection col = db.getCollection("notification");
		col.update(new BasicDBObject("_id",new ObjectId(notifId)),new BasicDBObject("$set",new BasicDBObject("vue?","TRUE")));
		return ServicesTools.ok();
	}
}
