package bd;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Database {
	private DataSource dataSource;
	
	public Database(String jndiname) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env/" +
					jndiname);
		} catch (Exception e) {
			// Handle error that it’s not configured in JNDI.
			throw new SQLException(jndiname + " is missing in JNDI! : "+e.getMessage());
		}
	}
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	

}
