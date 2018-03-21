package core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;



public class QueryFactory {
	


	private Connection conn;
	
	public QueryFactory(Connection conn) throws SQLException {
		this.conn = conn;
	}
	
	//------------------ SET-METODER -----------------------------
	
	public void setApparat(Apparat apparat) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Statement statement = conn.createStatement();
		String sql = String.format("insert into Apparat values ('%s', '%s')", apparat.getNavn(), apparat.getBeskrivelse());
		statement.executeUpdate(sql);
	}
	
	public void setOvelseUtenApparat(OvelseUtenApparat ovelse) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Statement statement = conn.createStatement();
		String sql1 = String.format("insert into Ovelse values ('%s', '%s')", ovelse.getNavn(), ovelse.getOvelsegruppe().getNavn());
		String sql2 = String.format("insert into OvelseUtenApparat values ('%s', '%s')", ovelse.getNavn(), ovelse.getBeskrivelse());
		statement.executeUpdate(sql1);
		statement.executeUpdate(sql2);
	}

	public void setOvelseMedApparat(OvelseMedApparat ovelse) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Statement statement = conn.createStatement();
		String sql1 = String.format("insert into Ovelse values ('%s', '%s')", ovelse.getNavn(), ovelse.getOvelsegruppe().getNavn());
		String sql2 = String.format("insert into OvelseMedApparat values ('%s', '%s')", ovelse.getNavn(), ovelse.getApparatnavn().getNavn());
		statement.executeUpdate(sql1);
		statement.executeUpdate(sql2);
	}
	
	public void setOvelseGruppe(OvelseGruppe og) throws SQLException {
		Statement statement = conn.createStatement();
		String sql = String.format("insert into Ovelsegruppe values ('%s')", og.getNavn());
		statement.executeUpdate(sql);
	}
	
	public void setNotat(Notat n) throws SQLException {
		Statement statement = conn.createStatement();
		String sql = String.format("insert into Notat values ('%s', '%s', '%s')", n.getNotatID(), n.getTreningsformal(), n.getOpplevelse());
		statement.executeUpdate(sql);
	}
	
	public void setTreningsokt(Treningsokt okt) throws SQLException {
		Statement statement = conn.createStatement();
		String sql = String.format("insert into Treningsokt values ('%s', '%s', '%s', '%s')", okt.getTreningsoktID(), okt.getDate(), okt.getVarighet(), okt.getNotat().getNotatID());
		statement.executeUpdate(sql);
	}
	
	public void setOvelseITreningsokt(Treningsokt okt, Ovelse ovelse) throws SQLException {
		Statement statement = conn.createStatement();
		String sql = String.format("insert into OvelseITreningsokt values ('%s', '%s', '%s', '%s', '%s')",
				okt.getTreningsoktID(), ovelse.getNavn(), ovelse.getKilo(), ovelse.getSets(), ovelse.getReps());
		statement.executeUpdate(sql);
	}
	
	//------------------ GET-METODER -----------------------------
	
	public List<Treningsokt> getSisteTreningsokter(int n) throws SQLException {
		Statement statement = conn.createStatement();
		String sql = String.format("select * from (Treningsokt natural join Notat) order by datotid desc  limit " + n);
		ResultSet rs = statement.executeQuery(sql);
		List<Treningsokt> okter = new ArrayList<Treningsokt>();
		while(rs.next()) {
			int treningsoktID = Integer.parseInt(rs.getString("treningsoktID"));
			String datotid = rs.getString("datotid");
			int varighet = Integer.parseInt(rs.getString("varighet"));
			Notat notat = getNotat(Integer.parseInt(rs.getString("notatID")));
			Treningsokt okt = new Treningsokt(treningsoktID, datotid, varighet, notat);
			okter.add(okt);
		}
		return okter;	
		
	}
	
	public Notat getNotat(int id) throws SQLException {
		Statement statement = conn.createStatement();
		String sql = "select * from Notat where notatID = " + id;
		ResultSet rs = statement.executeQuery(sql);
		Notat n = null;
		while (rs.next()) {
			String formaal = rs.getString("treningsformaal");
			String opplevelse = rs.getString("opplevelse");
			n = new Notat(id, formaal, opplevelse);
		}
		return n;
	}
	
	
	public List<Ovelse> getResultatLogg(String ovelse, String start, String slutt) throws SQLException {
		Statement statement = conn.createStatement();
		String sql = String.format("select * from (OvelseITreningsokt natural join Treningsokt natural join Ovelse) where navn = ('%s') and datotid >= ('%s') and datotid <= ('%s')", ovelse, start, slutt);
		ResultSet rs = statement.executeQuery(sql);
		List<Ovelse> ovelser = new ArrayList<Ovelse>();
		boolean ja = ovelseErMedApparat(ovelse);
		while (rs.next()) {
			// Felles for OMA og OUA er ovelsegruppe og performance
			OvelseGruppe ovelseGruppe = new OvelseGruppe(rs.getString("ovelsegruppeID"));
			int kg = Integer.parseInt(rs.getString("kilogram"));
			int sets = Integer.parseInt(rs.getString("sett"));
			int reps = Integer.parseInt(rs.getString("reps"));
			List<Integer> performance = Arrays.asList(kg, sets, reps);
			
			
			// Saeregent 
			if (ja) {
				Apparat apparat = getApparat(rs.getString("apparatnavn"));
				OvelseMedApparat oma = new OvelseMedApparat(ovelse, ovelseGruppe, apparat, performance);
				ovelser.add(oma);
			}
			else {
				String beskrivelse = getBeskrivelseFromOUA(ovelse);
				OvelseUtenApparat oua = new OvelseUtenApparat(ovelse, ovelseGruppe, beskrivelse, performance);
				ovelser.add(oua);
			}
		}
		return ovelser;
		}
		
	
	public boolean ovelseErMedApparat(String ovelse) throws SQLException {
		Statement statement = conn.createStatement();
		String sql = "select * from OvelseMedApparat";
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()){
			if (rs.getString("navn").equals(ovelse)){
				return true;
			}
		}
		return false;
		
	}
	
	public String getBeskrivelseFromOUA(String ovelse) throws SQLException {
		Statement statement = conn.createStatement();
		String sql = "select * from OvelseUtenApparat";
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			if (rs.getString("navn").equals(ovelse)){
				return rs.getString("beskrivelse");
			}
		}
		return null;
	}
	
	
	public Apparat getApparat(String apparatnavn) throws SQLException {
		Statement statement = conn.createStatement();
		String sql = "select * from Apparat";
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			if (rs.getString("navn").equals(apparatnavn)){
				Apparat a = new Apparat(rs.getString("navn"), rs.getString("beskrivelse"));
				return a;
			}
		}
		return null;
	}
	
	
	
		
	
	
	
	
	
	
	
	
	
}