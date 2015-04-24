package bd;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;

public class MapReduceTools {
	public final static String mDF = "function mapDF(){"
			+"	var word = this.text.match(/\\w+/g);"
			+"	if (word == null)"
			+"		return;"
			+"	var tf = [];"
			+"	for(var i=0;i< word.length;i++){"
			+"		if (tf[word[i]] == null){"
			+"			emit(word[i],{\"word\" : word[i],\"df\" : 1});"
			+"			tf[word[i]]=1;"
			+"		}"
			+"	}"
			+"}";
	
	public final static String rDF="function reduceDF(key, vals){"
			+"	var tot = 0;"
			+"	for(var i=0;i< vals.length;i++){"
			+"		tot +=vals[i].df;"
			+"	}"
			+"	return {\"word\" : key,\"df\" : tot};"
			+"}";
	
	public final static String mTF="function mapTF(){"
			+"	var word = this.text.match(/\\w+/g);"
			+"	if (word == null)"
			+"		return;"
			+"	for(var i=0;i< word.length;i++){"
			+"		emit({\"word\":word[i],\"commentID\":this._id}, {\"count\":1});"
			+"	}"
			+"}";
	
	public final static String rTF="function reduceTF(key, vals){"
			+"	var tot = 0;"
			+"	for(var i=0;i< vals.length;i++){"
			+"		tot +=vals[i].count;"
			+"	}"
			+"	return {\"word\":key.word,\"commentID\":key.commentID,\"count\":tot};"
			+"}";
}
