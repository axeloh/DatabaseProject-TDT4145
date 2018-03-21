package core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	
	
	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Connection conn = DBConnection.getDBConnection();
		QueryFactory qf = new QueryFactory(conn);
		
		OvelseGruppe og = new OvelseGruppe("Skuldre");
		OvelseUtenApparat oua = new OvelseUtenApparat("Hangups", og, "Beskrivelse", Arrays.asList(50, 20, 12));
//		qf.setOvelseGruppe(og);
//		qf.setOvelseUtenApparat(oua);
//		
//		Apparat apparat = new Apparat("Stativ", "...");
//		qf.setApparat(apparat);
//		OvelseMedApparat oma = new OvelseMedApparat("Pushdown", og, apparat);
//		qf.setOvelseMedApparat(oma);
		
		
//		Notat n = new Notat(1, "blabla", "blabla");
//		Treningsokt okt = new Treningsokt(1, "2018-03-19 14-00-00", 90, n);
//		qf.setNotat(n);
//		qf.setTreningsokt(okt);
//		qf.setOvelseITreningsokt(okt, oua);
//		Notat n2 = new Notat(2, "..", ",,,");
//		//qf.setNotat(n2);
//		qf.getNotat(1);
		
		List<Treningsokt> okter = qf.getSisteTreningsokter(5);
		okter.stream().forEach(o -> System.out.println(o.getDate()));
		System.out.println(qf.ovelseErMedApparat("benchpress"));
		System.out.println(qf.getBeskrivelseFromOUA("Bicepscurl"));
		System.out.println(qf.getApparat("Benk"));
		List<Ovelse> bench = qf.getResultatLogg("Benkpress", "1800-01-01 14:01:01", "3000-01-01 14:01:01");
		bench.stream().forEach(h -> System.out.println("Ovelse: " + h.getNavn() + ", Kg: " + h.getKilo() + ", Sets: " + h.getSets() + ", Reps: " + h.getReps() + ", Ovelsegruppe: " + h.getOvelsegruppe().getNavn()) );
		System.out.println(bench.size());
		
		
		//List<Ovelse> alle = qf.getAlleOvelser();
		//alle.stream().forEach(a -> System.out.println(a.getNavn()));
		System.out.println("----------------------");
		//List<Ovelse> skuldre = qf.getOvelserInOvelsegruppe("Skuldre");
		//skuldre.stream().forEach(s -> System.out.println(s.getNavn()));
		//System.out.println(alle.size());
		
	}

}
