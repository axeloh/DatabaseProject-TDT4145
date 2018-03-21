package core;

import java.util.List;

public class OvelseUtenApparat extends Ovelse{
	
	String beskrivelse;
	
	public OvelseUtenApparat(String navn, OvelseGruppe ovelsegruppe, String beskrivelse, List<Integer> performance) {
		this.navn = navn;
		this.ovelsegruppe = ovelsegruppe;
		if (performance != null) {
			this.kilo = performance.get(0);
			this.sets = performance.get(1);
			this.reps = performance.get(2);
		}
	}
	
	public OvelseUtenApparat(String navn, OvelseGruppe ovelsegruppe, String beskrivelse) {
		this.navn = navn;
		this.ovelsegruppe = ovelsegruppe;

	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}
	

	
}
