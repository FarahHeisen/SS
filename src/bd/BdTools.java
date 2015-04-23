package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.DBObject;

public abstract class BdTools {

	public static boolean userExists(String login) throws BDException {
		boolean retour; 
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DBStatic.getMySQLConnection();
			String query = "SELECT id FROM USERS WHERE login=\""+ login+ "\";";
			Statement ins = conn.createStatement();
			ins.executeQuery(query); // executeUpdate dans le cas d'une requete INSERT ou UPDATE ou CREATE
			ResultSet rs = ins.getResultSet();
			if(rs.next()) retour = true;
			else retour = false;
			rs.close();
			ins.close();
			conn.close();
		}	catch(SQLException e ){
			throw new BDException ("SQL Exception " + e.getMessage());
		}   catch(Exception e){
			throw new BDException ("Problem checking if user exists");
		}
		return retour;
	}

	public static boolean userExists(int id) throws BDException {
		boolean retour; 
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DBStatic.getMySQLConnection();
			String query = "SELECT id FROM USERS WHERE id="+ id+ ";";
			Statement ins = conn.createStatement();
			ins.executeQuery(query); // executeUpdate dans le cas d'une requete INSERT ou UPDATE ou CREATE
			ResultSet rs = ins.getResultSet();
			if(rs.next()) retour = true;
			else retour = false;
			rs.close();
			ins.close();
			conn.close();
		}	catch(SQLException e ){
			throw new BDException ("SQL Exception " + e.getMessage());
		}   catch(Exception e){
			throw new BDException ("Problem checking if user exists");
		}
		return retour;
	}

	public static void createUser(String login, String pwd,String prenom, String nom) throws BDException {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DBStatic.getMySQLConnection();
			//Requete 
			String query = "INSERT INTO USERS VALUES (null,\""+login+ "\",\""+ pwd + "\",\""+ prenom+ "\",\"" +nom +"\");";
			System.out.println(query);
			Statement ins = conn.createStatement();
			ins.executeUpdate(query); // executeUpdate dans le cas d'une requete INSERT ou UPDATE ou CREATE ou DELETE
			ins.close();
			conn.close();


		}catch(Exception e ){
			throw new BDException (e.getMessage());
		}
	}



	public static void deleteUser(String login) throws BDException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DBStatic.getMySQLConnection();
			//Requete 
			String query = "DELETE FROM USERS WHERE login=\""+ login +"\";" ;
			System.out.println(query);
			Statement ins = conn.createStatement();
			ins.executeUpdate(query); // executeUpdate dans le cas d'une requete INSERT ou UPDATE ou DELETE
			ins.close();
			conn.close();
		}catch(Exception e ){
			throw new BDException ("Problem deleting the user " +e.getMessage());
		}
	}
	
	//Il faut retiré logKey ou ID ou ne doit en transporté que 1 dans les pages
	public static boolean userConnected(String id, String logKey)throws BDException {
		boolean retour; 
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DBStatic.getMySQLConnection();
			String query = "SELECT * FROM AUTHENTIFICATION WHERE id=\""+ id+ "\" AND logKey=\"" +logKey+ "\";";
			Statement ins = conn.createStatement();
			ins.executeQuery(query); // executeUpdate dans le cas d'une requete INSERT ou UPDATE ou CREATE
			ResultSet rs = ins.getResultSet();
			if(rs.next()) retour = true;
			else retour = false;
			rs.close();
			ins.close();
			conn.close();
		}	catch(SQLException e ){
			throw new BDException ("SQL Exception " + e.getMessage());
		}   catch(Exception e){
			throw new BDException ("Problem checking if user exists");
		}
		return retour;
	}
	
	public static  boolean checkPassword(String login, String password) throws BDException {
		boolean retour = false; 
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DBStatic.getMySQLConnection();
			//Requete 
			String query = "SELECT id FROM USERS WHERE login=\""+ login +"\" AND pwd=\""+ password +"\";" ;
			Statement ins = conn.createStatement();
			ins.executeQuery(query); // executeUpdate dans le cas d'une requete INSERT ou UPDATE ou DELETE
			ResultSet rs = ins.getResultSet();
			if(rs.next()) retour = true;
			rs.close();
			ins.close();
			conn.close();
		}catch(Exception e ){
			throw new BDException ("Problem checking login and password -> " +e.getMessage());
		}
		return retour;
	}
	
	public static  boolean checkConnection(String login) throws BDException {
		boolean retour = false; 
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DBStatic.getMySQLConnection();
			//Requete 
			String query = "SELECT a.id FROM USERS u, AUTHENTIFICATION a WHERE u.login=\""+ login +"\" AND u.id=a.id;" ;
			Statement ins = conn.createStatement();
			ins.executeQuery(query); // executeUpdate dans le cas d'une requete INSERT ou UPDATE ou DELETE
			ResultSet rs = ins.getResultSet();
			if(rs.first()) retour = true;
			rs.close();
			ins.close();
			conn.close();
		}catch(Exception e ){
			throw new BDException ("Problem checking login and password -> " +e.getMessage());
		}
		return retour;
	}
	
	public static  boolean checkAuthentification(String key) throws BDException {
		boolean retour = false; 
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DBStatic.getMySQLConnection();
			//Requete 
			String query = "SELECT a.logKey FROM AUTHENTIFICATION a WHERE a.logKey=\""+ key +"\";" ;
			Statement ins = conn.createStatement();
			ins.executeQuery(query); // executeUpdate dans le cas d'une requete INSERT ou UPDATE ou DELETE
			ResultSet rs = ins.getResultSet();
			if(rs.first()) retour = true;
			rs.close();
			ins.close();
			conn.close();
		}catch(Exception e ){
			throw new BDException ("Problem checking  -> " +e.getMessage());
		}
		return retour;
	}

	/**
	 * Ajoute la clef donnée pour la session de l'utilisateur et lui active sa
	 * session
	 * 
	 * @param id
	 *            id de l'utilisateur
	 * @param clef
	 *            sa clef
	 * @throws BDException
	 */
	public static void deleteKey(String key) throws BDException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String string = "DELETE FROM AUTHENTIFICATION WHERE logKey=\"" + key +"\";";

			Connection c = DBStatic.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeUpdate(string);

			// Fermeture de la connexion
			s.close();
			c.close();
		} catch (Exception e) {
			throw new BDException("delete KEY " + e.getMessage());
		}
	}
	
	public static void logoutId(int id) throws BDException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String string = "DELETE FROM AUTHENTIFICATION WHERE id=\"" + id +"\";";

			Connection c = DBStatic.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeUpdate(string);

			// Fermeture de la connexion
			s.close();
			c.close();
		} catch (Exception e) {
			throw new BDException("logout id " + e.getMessage());
		}

	}
	/**
	 * Ajoute la clef donnée pour la session de l'utilisateur et lui active sa
	 * session
	 * 
	 * @param id
	 *            id de l'utilisateur
	 * @param clef
	 *            sa clef
	 * @throws BDException
	 */
	public static void addKey(int id, String key) throws BDException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String string = "INSERT INTO AUTHENTIFICATION VALUE ("+ id +",\"" + key + "\");";

			Connection c = DBStatic.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeUpdate(string);

			// Fermeture de la connexion
			s.close();
			c.close();
		} catch (Exception e) {
			throw new BDException("add KEY " + e.getMessage());
		}

	}
	
	/**
	 * 
	 * renvoie l'id d'un utilisateur
	 * @throws BDException 
	 */
	public static int returnId(String login) throws BDException{
		int id = -1;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String string = "SELECT id FROM USERS WHERE login=\"" + login +"\";";
			Connection c = DBStatic.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(string);
			ResultSet rs = s.getResultSet();
			if(rs.first())
				id = Integer.parseInt(rs.getString("id"));
			else
				throw new Exception("login not found");
			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();
		} catch (Exception e) {
			throw new BDException("error looking for id : " + e.getMessage());
		}
		return id;
		
	}
	
	public static int keyLog2Id(String key) throws BDException{
		int id = -1;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String string = "SELECT id FROM AUTHENTIFICATION WHERE logKey=\"" + key +"\";";
			Connection c = DBStatic.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(string);
			ResultSet rs = s.getResultSet();
			if(rs.next())
				id = Integer.parseInt(rs.getString("id"));
			else
				throw new Exception("login not found");
			// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();
		} catch (Exception e) {
			throw new BDException(" error looking for id : " + e.getMessage());
		}
		return id;
		
	}
	
	public static void addFollower(int my_id, int his_id) throws BDException{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String string = "INSERT INTO FOLLOWERS VALUE ("+ my_id +",\"" + his_id + "\");";

			Connection c = DBStatic.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeUpdate(string);

			// Fermeture de la connexion
			s.close();
			c.close();
		} catch (Exception e) {
			throw new BDException("add KEY " + e.getMessage());
		}

	}

	public static void removeFollower(int my_id, int his_id) throws BDException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String string = "DELETE FROM FOLLOWERS WHERE id_user=\"" + my_id +"\" AND id_followed=\""+his_id+"\";";

			Connection c = DBStatic.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeUpdate(string);

			// Fermeture de la connexion
			s.close();
			c.close();
		} catch (Exception e) {
			throw new BDException("unStalk id " + e.getMessage());
		}

	}
	
	public static ArrayList<Integer> getStalked(String key) throws BDException{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			ArrayList<Integer> stalked = new ArrayList<Integer>();
			int id = BdTools.keyLog2Id(key);
			String string = "SELECT ID_FOLLOWED FROM FOLLOWERS WHERE ID_USER=" + id +";";
			Connection c = DBStatic.getMySQLConnection();
			Statement s = c.createStatement();
			s.executeQuery(string);
			ResultSet rs = s.getResultSet();
			while(rs.next()){
				stalked.add(rs.getInt("ID_FOLLOWED"));
			}
		// Fermeture de la connexion
			rs.close();
			s.close();
			c.close();
			return stalked;
		} catch (Exception e) {
			throw new BDException("get Stalked " + e.getMessage());
		}
	}

	public static ArrayList<JSONObject> listUserJS(ArrayList<String> listeAuthor, String key) throws BDException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException{
		ArrayList<Integer> amis=new ArrayList<>();
		if(key!=null)
			amis = BdTools.getStalked(key);
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection c = DBStatic.getMySQLConnection();
		Statement s = c.createStatement();
		
		String query = "SELECT id, login FROM USERS WHERE id IN ("+listeAuthor.get(0);
		for(int i =1; i<listeAuthor.size();i++){
			query+=", \""+listeAuthor.get(i)+"\"";
		}
		query +=");";
		
		s.executeQuery(query);
		ResultSet rs = s.getResultSet();
		ArrayList<JSONObject> res = new ArrayList<JSONObject>();
		while(rs.next()){
			JSONObject elem =new JSONObject();
			elem.put("id", rs.getInt("id"));
			elem.put("login", rs.getString("login"));
			if(amis.contains(rs.getInt("id")))
				elem.put("contact",false);
			else
				elem.put("contact", false);
		}
		return res;
	}

}