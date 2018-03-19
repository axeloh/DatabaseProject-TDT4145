package core;

import java.util.List;

public class OvelseMedApparat extends Ovelse{
	
	Apparat apparat;
	
	public OvelseMedApparat(String navn, OvelseGruppe ovelsegruppe, Apparat apparat, List<Integer> performance) {
		this.navn = navn;
		this.ovelsegruppe = ovelsegruppe;
		this.apparat = apparat;
		if (performance != null) {
			this.kilo = performance.get(0);
			this.sets = performance.get(1);
			this.reps = performance.get(2);
		}
	}

	public Apparat getApparatnavn() {
		return apparat;
	}
	
	public void setApparatnavn(Apparat apparatnavn) {
		this.apparat = apparatnavn;
	}
	
	


}
