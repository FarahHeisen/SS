package services;

import java.net.UnknownHostException;

import org.json.JSONObject;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoException;

import bd.BDException;
import bd.BdTools;
import bd.DBStatic;
import bd.MongoTools;

public class ShareService {
	public static JSONObject share(String logKey, String idMsg, int id_author){
		int idUser = -1;
		DB db;
		try {
			idUser = BdTools.keyLog2Id(logKey);
			db = DBStatic.getMongoDB();
		} catch (BDException e) {
			return ServicesTools.error("Share SQL erreur : " , e.getMessage());
		} catch (UnknownHostException e) {
			return ServicesTools.error("Share Mongo erreur : " , e.getMessage());
		} catch (MongoException e) {
			return ServicesTools.error("Share Mongo erreur : " , e.getMessage());
		}
		
		DBCollection col = db.getCollection("comments");
		BasicDBObject obj = new BasicDBObject();
		obj.put("$push", new BasicDBObject("sharedBy", ""+idUser));
		col.update(new BasicDBObject("_id", new ObjectId(idMsg)), obj);
		MongoTools.notify(id_author, "share", idUser);
		return ServicesTools.ok();
	}
}
