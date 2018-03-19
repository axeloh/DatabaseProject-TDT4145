import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {
	
private static Connection conn;
	
	// Statisk funksjon for aa koble seg opp mot databasen.
	
	public static Connection getDBConnection() {
		try {
			String url = "jdbc:mysql://mysql.stud.ntnu.no:3306/theodoaw_mcFriends?autoReconnect=true&useSSL=false";
			String user = "theodoaw";
			String pass = "theodor";
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("Connected to the database");
		} catch (Exception e) {
			System.err.println("Got an exception...");
			System.err.println(e.getMessage());
		}
		return conn;
	}

}
