package bd;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class DBStatic {
	private static boolean mysql_pooling=false;
	private static String mysql_db = "gr3_dhou_bert";
	private static String mysql_username = "gr3_dhou_bert";
	private static String mysql_password = "gr3_dhou_bert$";
	private static String mysql_host = "132.227.201.129:33306";
	private static Database database;
	private static String mongo_dBname= "gr3_dhou";
	private static char[] mongo_pwd = {'g','r','3','_','d','h','o','u','_','b','e','r','t','$'};
	private static String mongo_username = "gr3_dhou_bert";
	
	public static Connection getMySQLConnection() throws SQLException
	{
		if (DBStatic.mysql_pooling==false)
		{
			return( DriverManager.getConnection("jdbc:mysql://"+DBStatic.mysql_host
					+"/"
					+DBStatic.mysql_db, DBStatic.mysql_username,DBStatic.mysql_password));
		}
		else
		{
			if (database==null)
			{
				database = new Database("jdbc/db");
			}
			return(database.getConnection());
		}
	}
	
	public static DB getMongoDB() throws UnknownHostException, MongoException{
		Mongo m = new Mongo("132.227.201.129", 27130);
		DB  d = m.getDB(DBStatic.mongo_dBname);
		d.authenticate(mongo_username , mongo_pwd);
		return d;
	}
}
