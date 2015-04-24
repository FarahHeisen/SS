package services;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import bd.BDException;
import bd.BdTools;
import bd.DBStatic;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

public class FindingService {

	/**
	 * 
	 * @param author
	 * @param after
	 * @param before
	 * @param followed
	 * @param shared
	 * @return
	 * @throws BDException 
	 */
	public static JSONObject find(String key, String[] author,String[] hashtag,Date after,Date before,boolean followed,boolean shared/*,String[] keyWord*/){

		/* Nos conditions  : les auteurs*/
		BasicDBObject masque = new BasicDBObject();
		BasicDBList or1 = new BasicDBList();
		if(author != null){
			or1.add( new BasicDBObject("author_id", new BasicDBObject("$in", author)));
		}
		if(hashtag != null){
			masque.put("hashTag", new BasicDBObject("$in", hashtag));
		}
			/*Dates*/
		if(after != null)
			masque.put("date", new BasicDBObject("$gt",after));
		if(before != null)
			masque.put("date", new BasicDBObject("$lt",before));
		
		// si c'est vrai, renvoie les messages doivent être écrit par des followers
		if((followed) && (key!=null)){
			ArrayList<Integer> followedArray = null;
			try {
				followedArray = BdTools.getStalked(key);
			} catch (BDException e) {
				return ServicesTools.error("getSaltked :", e.getMessage());
			}
			
			if(followedArray.size()>0){
				String[] followedTab = new String[followedArray.size()];
				for(int i =0; i<followedArray.size(); i++){
					followedTab[i] = ""+followedArray.get(i);
				}
				BasicDBList or2 = new BasicDBList();
				or2.add(new BasicDBObject("author_id", new BasicDBObject("$in", followedTab)));
				if(shared)
					or2.add(new BasicDBObject("sharedBy",new BasicDBObject("$in", followedTab)));
				if(!or2.isEmpty())
					masque.put("$or", or2);

			}
		}
		
		if((shared)&&(author != null)){
			or1.add(new BasicDBObject("sharedBy",new BasicDBObject("$in", author)));
		}
		if(!or1.isEmpty())
			masque.put("$or", or1);
		JSONObject res = new JSONObject();
		ArrayList<Integer> listeAuthor=new ArrayList<>();
		try {
			DB db = DBStatic.getMongoDB();
			DBCollection col = db.getCollection("comments");
			DBCursor cursor = col.find(masque);
			ArrayList<DBObject> listeComm=new ArrayList<>();
			while(cursor.hasNext()){
				DBObject o = cursor.next();
				listeComm.add(o);
				int id=Integer.parseInt((String)o.get("author_id"));
				if(!listeAuthor.contains(id))
					listeAuthor.add(id );
			}
			res.put("comment", listeComm); // liste des commentaires 
			res.put("author", BdTools.listUserJS(listeAuthor, key));
		}catch (MongoException e) {
			return ServicesTools.error("MongoException", e.getMessage());
		} catch (InstantiationException e) {
			return ServicesTools.error("InstantiationException", e.getMessage());
		} catch (IllegalAccessException e) {
			return ServicesTools.error("IllegalAccessException ", e.getMessage());
		} catch (ClassNotFoundException e) {
			return ServicesTools.error("ClassNotFoundException", e.getMessage());
		} catch (JSONException e) {
			return ServicesTools.error("JSONException", e.getMessage());
		} catch (BDException e) {
			return ServicesTools.error("BDException", e.getMessage());
		} catch (SQLException e) {
			return ServicesTools.error("SQLException", e.getMessage());
		} catch (UnknownHostException e) {
			return ServicesTools.error("UnknownHostException", e.getMessage());
		}
		return res;
	}
}
