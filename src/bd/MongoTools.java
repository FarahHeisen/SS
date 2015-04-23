package bd;

import java.net.UnknownHostException;
import java.util.GregorianCalendar;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoException;

public class MongoTools {
	public static void notify(int cible_id, String msg,int sender_id){
		DB db=null;
		try {
			db = DBStatic.getMongoDB();
		} catch (UnknownHostException | MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBCollection col = db.getCollection("notification");
		BasicDBObject obj= new BasicDBObject();
		
		obj.put("cible_id",""+cible_id);
		obj.put("message",msg);
		obj.put("date",(new GregorianCalendar()).getTime());
		obj.put("vue", "FALSE");
		obj.put("sender_id",""+sender_id);
		col.insert(obj);
	}
	
}
