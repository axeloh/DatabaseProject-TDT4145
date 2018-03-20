package core;

import java.util.List;

public class OvelseUtenApparat extends Ovelse{
	
	String beskrivelse;
	
	public OvelseUtenApparat(String navn, OvelseGruppe ovelsegruppe, String beskrivelse) {
		this.navn = navn;
		this.ovelsegruppe = ovelsegruppe;
		this.beskrivelse = beskrivelse;
		
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}
	

	
}
