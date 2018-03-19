package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class QueryFactory {
	

	private Statement statement;
	
	public QueryFactory(Connection conn) throws SQLException {
		statement = conn.createStatement();
	}
	
//	public static PreparedStatement setApparat(Apparat apparat) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//		PreparedStatement stmt = conn.prepareStatement("insert into Apparat values (?, ?) ");
//		stmt.setString(0, apparat.getNavn());
//		stmt.setString(1, apparat.getBeskrivelse());
//		return stmt;
//	}
	
	public void setApparat(Apparat apparat) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String sql = String.format("insert into Apparat values (%s, %s)", apparat.getNavn(), apparat.getBeskrivelse());
		statement.executeUpdate(sql);
	}
	
//	public static PreparedStatement setOvelseUtenApparat(OvelseUtenApparat ovelse) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//		PreparedStatement stmt = conn.prepareStatement("insert into Ovelse values (?, ?, ?)"); 
//		stmt.setString(0, ovelse.getNavn());
//		stmt.setString(1, ovelse.getOvelsegruppe().getNavn());
//		stmt.setString(2, ovelse.getBeskrivelse());
//		return stmt;
//	}
	
	public void setOvelseUtenApparat(OvelseUtenApparat ovelse) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String sql1 = String.format("insert into Ovelse values (%s, %s)", ovelse.getNavn(), ovelse.getOvelsegruppe().getNavn());
		String sql2 = String.format("insert into OvelseUtenApparat values (%s, %s)", ovelse.getNavn(), ovelse.getBeskrivelse());
		statement.executeUpdate(sql1);
		statement.executeUpdate(sql2);
	}
	
	
	
	

}
