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
	
	public void setApparat(Apparat apparat) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String sql = String.format("insert into Apparat values ('%s', '%s')", apparat.getNavn(), apparat.getBeskrivelse());
		statement.executeUpdate(sql);
	}
	
	public void setOvelseUtenApparat(OvelseUtenApparat ovelse) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String sql1 = String.format("insert into Ovelse values ('%s', '%s')", ovelse.getNavn(), ovelse.getOvelsegruppe().getNavn());
		String sql2 = String.format("insert into OvelseUtenApparat values ('%s', '%s')", ovelse.getNavn(), ovelse.getBeskrivelse());
		statement.executeUpdate(sql1);
		statement.executeUpdate(sql2);
	}
	

	public void setOvelseMedApparat(OvelseMedApparat ovelse) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String sql1 = String.format("insert into Ovelse values ('%s', '%s')", ovelse.getNavn(), ovelse.getOvelsegruppe().getNavn());
		String sql2 = String.format("insert into OvelseMedApparat values ('%s', '%s')", ovelse.getNavn(), ovelse.getApparatnavn().getNavn());
		statement.executeUpdate(sql1);
		statement.executeUpdate(sql2);
	}
	
	public void setOvelseGruppe(OvelseGruppe og) throws SQLException {
		String sql = String.format("insert into Ovelsegruppe values ('%s')", og.getNavn());
		statement.executeUpdate(sql);
	}
	
	
}