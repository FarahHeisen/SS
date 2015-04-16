

import java.sql.Connection;
import java.util.ArrayList;

import bd.BdTools;
import bd.DBStatic;

public class Main {

	public static void main (String [] args){
				    try {
			      Class.forName("com.mysql.jdbc.Driver").newInstance();
			      System.out.println("Driver O.K.");

			     // String url = "jdbc:mysql://132.227.201.129:33306/";
			     // String user = "gr3_dhou_bert";
			     // String passwd = "gr3_dhou_bert$";

			      Connection conn = DBStatic.getMySQLConnection();
			      System.out.println("Connexion effective !");         
			      ArrayList<Integer> id = BdTools.getStalked("0mnngrsf3yp9nw9md121tvn7m4dva05z");
			      
			    System.out.println("id = "+id);
			      conn.close();
			    } catch (Exception e) {
			      e.printStackTrace();
			    }
	}
	
}
