package services;

import java.sql.Connection;
import java.sql.DriverManager;

import bd.BdTools;
import bd.DBStatic;

public class Main {

	public static void main (String [] args){
				    try {
			      Class.forName("com.mysql.jdbc.Driver").newInstance();
			      System.out.println("Driver O.K.");

			      String url = "jdbc:mysql://132.227.201.129:33306/";
			      String user = "gr3_dhou_bert";
			      String passwd = "gr3_dhou_bert$";

			      Connection conn = DBStatic.getMySQLConnection();
			      System.out.println("Connexion effective !");         
			      if (BdTools.userExists("MarciaP.Nelson"))
			    	  BdTools.deleteUser("MarciaP.Nelson");
			    else
			    	  System.out.println("user dont exist");
			    System.out.println("Hello");
			      conn.close();
			    } catch (Exception e) {
			      e.printStackTrace();
			    }      
		
	}
	
}
