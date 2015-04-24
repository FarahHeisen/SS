package services;

import org.json.JSONObject;

import bd.DBStatic;
import bd.MapReduceTools;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;

public class MapReduceService {
	public static JSONObject updateBD(){

		DB db;
		DBCollection col = null ;
		try {
			db = DBStatic.getMongoDB();
			col = db.getCollection("comments");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MapReduceOutput outDF = col.mapReduce(MapReduceTools.mDF, MapReduceTools.rDF, null, MapReduceCommand.OutputType.INLINE,null);
		MapReduceOutput outTF = col.mapReduce(MapReduceTools.mTF, MapReduceTools.rTF, null, MapReduceCommand.OutputType.INLINE,null);
		return null;
	}
}
