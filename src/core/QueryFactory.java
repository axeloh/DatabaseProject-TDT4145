package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class QueryFactory {
	

	private static Connection conn;
	
	public QueryFactory(Connection conn) {
		this.conn = conn;
	}
	
	public static PreparedStatement setApparat(Apparat apparat) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		PreparedStatement stmt = conn.prepareStatement("insert into Apparat values (?, ?) ");
		stmt.setString(0, apparat.getNavn());
		stmt.setString(1, apparat.getBeskrivelse());
		return stmt;
	}
	
	public static PreparedStatement setOvelseUtenApparat(OvelseUtenApparat ovelse) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		PreparedStatement stmt = conn.prepareStatement("insert into Ovelse values (?, ?, ?)"); 
		stmt.setString(0, ovelse.getNavn());
		stmt.setString(1, ovelse.getOvelsegruppe().getNavn());
		stmt.setString(2, ovelse.getBeskrivelse());
		return stmt;
	}
	
	public static PreparedStatement setOvelseMedApparat(OvelseMedApparat ovelse) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		PreparedStatement stmt = conn.prepareStatement("insert into Ovelse values (?, ?, ?)"); 
		stmt.setString(0, ovelse.getNavn());
		stmt.setString(1, ovelse.getOvelsegruppe().getNavn());
		stmt.setString(2, ovelse.getApparatnavn().getNavn());
		return stmt;
	}
	
	
	

}