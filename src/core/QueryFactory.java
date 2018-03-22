package core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;




public class QueryFactory {
	


	private Connection conn;
	
	public QueryFactory(Connection conn) throws SQLException {
		this.conn = conn;
	}
	
	
//------------------------- SET-METODER ----------------------------------

	// Krav 1: Registrere apparater, ovelser og treningsokter med tilhorende data
	
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
	
	
//-------------------------- GET-METODER ---------------------------------
	
	// Krav 2: Info om n siste gjennomførte treningsokter
	// Returnerer en liste med de n siste treningsoktene, der hver treningsokt har alle sine ovelser (med og uten apparat) lagt inn
	// Dersom n er null vil alle treningsokter returneres
	public List<Treningsokt> getSisteTreningsokter(Integer n) throws SQLException {
		Statement statement = conn.createStatement();
		
		String sql = n != null ? String.format("select * from (Treningsokt natural join Notat) order by datotid desc  limit " + n)
				: String.format("select * from (Treningsokt natural join Notat) order by datotid desc");
		ResultSet rs = statement.executeQuery(sql);
		List<Treningsokt> okter = new ArrayList<Treningsokt>();
		
		// For hver rad opprett treningsokt-objekt og legg til i lista
		while(rs.next()) {
			int treningsoktID = Integer.parseInt(rs.getString("treningsoktID"));
			String datotid = rs.getString("datotid");
			int varighet = Integer.parseInt(rs.getString("varighet"));
			Notat notat = getNotat(Integer.parseInt(rs.getString("notatID")));
			Treningsokt okt = new Treningsokt(treningsoktID, datotid, varighet, notat);
			okter.add(okt);
		}
		//okter.stream().forEach(o -> System.out.println("dato: " + o.getDate() + "| id: " + o.getTreningsoktID()));
		 
		// For hver treningsokt legg til deres ovelser
		for (Treningsokt okt : okter) {
			
			// Forst ovelser uten apparat
			statement = conn.createStatement();
			String sql2 = String.format("select * from (Ovelse natural join OvelseITreningsokt natural join OvelseUtenApparat) where treningsoktID = ('%s')", okt.getTreningsoktID());
			ResultSet rs2 = statement.executeQuery(sql2);
			while (rs2.next()) {
				String navn = rs2.getString("navn");
				OvelseGruppe og = new OvelseGruppe(rs2.getString("ovelsegruppeID"));
				String beskrivelse = rs2.getString("beskrivelse");
				int kg = Integer.parseInt(rs2.getString("kilogram"));
				int sets = Integer.parseInt(rs2.getString("sett"));
				int reps = Integer.parseInt(rs2.getString("reps"));
				List<Integer> performance = Arrays.asList(kg, sets, reps);
				Ovelse ovelse = new OvelseUtenApparat(navn, og, beskrivelse, performance);
				okt.addOvelse(ovelse);
			}
			
			// Saa ovelser med apparat
			statement = conn.createStatement();
			String sql3 = String.format("select * from (Ovelse natural join OvelseITreningsokt natural join OvelseMedApparat)"
					+ " inner join Apparat on apparatnavn = Apparat.navn"
					+ " where treningsoktID = ('%s')", okt.getTreningsoktID());
			ResultSet rs3 = statement.executeQuery(sql3);
			while (rs3.next()) {
				String navn = rs3.getString("navn");
				OvelseGruppe og = new OvelseGruppe(rs3.getString("ovelsegruppeID"));
				Apparat apparat = new Apparat(rs3.getString("navn"), rs3.getString("beskrivelse"));
				int kg = Integer.parseInt(rs3.getString("kilogram"));
				int sets = Integer.parseInt(rs3.getString("sett"));
				int reps = Integer.parseInt(rs3.getString("reps"));
				List<Integer> performance = Arrays.asList(kg, sets, reps);
				Ovelse ovelse = new OvelseMedApparat(navn, og, apparat, performance);
				okt.addOvelse(ovelse);
			}
		}
		
		return okter;	
		
	}
	
	
	
	// Krav 3: Resultatlogg for ovelse i gitt tidsintervall
	public List<Ovelse> getResultatLogg(String ovelse, String start, String slutt) throws SQLException {
		List<Treningsokt> alleOkter = getSisteTreningsokter(null);
		List<Treningsokt> OkterIIntervallet = alleOkter.stream().
				filter(t -> start.compareTo(t.getDate()) <= 0 && t.getDate().compareTo(slutt) <= 0).collect(Collectors.toList());
		List<Ovelse> ovelser = new ArrayList<>();
		for (Treningsokt okt : OkterIIntervallet) {
			okt.getOvelser().stream().filter(o -> o.getNavn().equals(ovelse)).forEach(o -> ovelser.add(o));
		}
		return ovelser;
	}
	
	
	// Krav 4: Finne ovelser i samme ovelsegruppe
	public List<Ovelse> getOvelserInOvelsegruppe(String ovelsegruppe) throws SQLException{
		List<Ovelse> ovelser = getAlleOvelser();
		return ovelser.stream().filter(o -> o.ovelsegruppe.getNavn().equals(ovelsegruppe)).collect(Collectors.toList());
		
	}
	
	
	// Krav 5: Antall ganger en ovelse har blitt gjort
	public int getAntallGangerOvelseUtfort(String ovelse) throws SQLException {
		return getResultatLogg(ovelse, "0000-00-00 00:00:00", "9999-12-30 23:59:59").size();
	}
	

	
	

//  ----------------------- HJELPEMETODER -----------------------------
	
	
	public List<Ovelse> getAlleOvelser() throws SQLException{
		List<Ovelse> alleOvelser = new ArrayList<>();
		
		// Forst ovelser uten apparat
		Statement statement = conn.createStatement();
		String sql = String.format("select * from (Ovelse natural join OvelseUtenApparat)");
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			String navn = rs.getString("navn");
			OvelseGruppe og = new OvelseGruppe(rs.getString("ovelsegruppeID"));
			String beskrivelse = rs.getString("beskrivelse");
			Ovelse ovelse = new OvelseUtenApparat(navn, og, beskrivelse, null);
			alleOvelser.add(ovelse);
		}

		// Saa ovelser med apparat
		statement = conn.createStatement();
		sql = String.format("select * from (Ovelse natural join OvelseMedApparat) inner join Apparat on apparatnavn = Apparat.navn");
		rs = statement.executeQuery(sql);
		while (rs.next()) {
			String navn = rs.getString("navn");
			OvelseGruppe og = new OvelseGruppe(rs.getString("ovelsegruppeID"));
			Apparat apparat = new Apparat(rs.getString("apparatnavn"), rs.getString("beskrivelse"));
			Ovelse ovelse = new OvelseMedApparat(navn, og, apparat, null);
			alleOvelser.add(ovelse);
		}
		
		return alleOvelser;	
	}
	
	public List<OvelseGruppe> getAlleOvelsegrupper() throws SQLException{
		Statement statement = conn.createStatement();
		String sql = "select * from Ovelsegruppe ";
		ResultSet rs = statement.executeQuery(sql);
		List<OvelseGruppe> alleGrupper = new ArrayList<>();
		while (rs.next()) {
			alleGrupper.add(new OvelseGruppe(rs.getString("navn")));
		}
		return alleGrupper;
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
	
	public Apparat getApparat(String apparatnavn) throws SQLException {
		Statement statement = conn.createStatement();
		String sql = String.format("select * from Apparat where navn = ('%s')", apparatnavn);
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
				Apparat a = new Apparat(rs.getString("navn"), rs.getString("beskrivelse"));
				return a;
		}
		return null;
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
	
	
	
	
		
	
	
	
	
	
	
	
	
	
}