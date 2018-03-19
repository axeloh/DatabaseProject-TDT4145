package core;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
	
	
	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		DBConnection dbs = new DBConnection();
		Connection conn = dbs.getDBConnection();
		QueryFactory qf = new QueryFactory(conn);
		
		OvelseGruppe og = new OvelseGruppe("Skuldre");
//		OvelseUtenApparat oua = new OvelseUtenApparat("Hangups", og, "Beskrivelse");
//		qf.setOvelseGruppe(og);
//		qf.setOvelseUtenApparat(oua);
		
		Apparat apparat = new Apparat("Stativ", "...");
		qf.setApparat(apparat);
		OvelseMedApparat oma = new OvelseMedApparat("Pushdown", og, apparat);
		qf.setOvelseMedApparat(oma);
		
		
	
	}

}
