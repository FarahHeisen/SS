package services;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import bd.BdTools;
import bd.DBStatic;
import bd.MongoTools;

public class CommentService {
	public static JSONObject comment(String key, String comm){
		JSONObject retour= ServicesTools.error("comment_error", "1");
		try {
			int a_id=BdTools.keyLog2Id(key);
			DB db = DBStatic.getMongoDB();
			DBCollection col = db.getCollection("comments");
			BasicDBObject obj = new BasicDBObject();
			obj.put("author_id", ""+a_id);
			obj.put("text",  comm);
			obj.put("date", (new GregorianCalendar()).getTime());
			//tentative HashTag
			ArrayList<String> hashtag = new ArrayList<String>();
			ArrayList<String> citation_id = new ArrayList<String>();
			int i =0;
			while( i<comm.length()){
				if(comm.charAt(i)=='#'){
					String tag="";
					while((i<comm.length())&&(comm.charAt(i) != ' ')){
						tag += comm.charAt(i);
						i++;
					}
					hashtag.add(tag);
				}
				if(comm.charAt(i)=='@'){
					i++;
					String citation_log="";
					while((i<comm.length())&&(comm.charAt(i) != ' ')){
						citation_log += comm.charAt(i);
						i++;
					}
					int cible_id =BdTools.returnId(citation_log);
					citation_id.add(""+cible_id);
					MongoTools.notify(cible_id, "quote",a_id);
				}
				i++;
			}
			obj.put("hashTag", hashtag);
			col.insert(obj);
			retour = ServicesTools.ok();
		} catch (Exception e) {
			retour = ServicesTools.error(e.getClass()+"", e.getMessage());
		} 
		return retour;
	}
}
