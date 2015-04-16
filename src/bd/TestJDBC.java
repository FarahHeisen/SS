package bd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class TestJDBC {

	public static void main (String [] args) throws Exception {
		
			//Connexion
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://132.227.201.129:33306/nomBase";
			Connection conn = DriverManager.getConnection(url, "gr2-Br"/*LOGIN*/, "mdp");
			//Requete 
			String query = "SELECT * FROM books";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query); // executeUpdate dans le cas d'une requete INSERT ou UPDATE ou CREATE
			while(rs.next()){
				String title = rs.getString("title");					
				String id = rs.getString(1);
				System.out.println(title + " " + id);
			}
		
		st.close();
		conn.close();
	
	}
	
}

/*
 * A STOCKER : utilisateut = id, login, password, nom, prenom.....
 * 			AMis 
 * 			Sessions Id <-> IdSession, TimeStamp
 * 
 * 			Photos
 * 
 * exemple creation de table
 * 
 * CREATE TABLE users (
 * 	id INTEGER PRIMARY KEY auto_increment, 
 * 	login VARCHAR(32) UNIQUE,
 * 	pwd 
 * );
 * 
 * 
 * requete que l'on peut envoyer avec executeUpdate 
 * INSERT INTO USERS VALUES (null, \"login\",\"1234\", \"prénom\", \'nom\');
 * 
 * 
 * 
 */
