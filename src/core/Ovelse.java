package core;

public abstract class Ovelse {

	String navn;
	OvelseGruppe ovelsegruppe;
	
	
	public String getNavn() {
		return navn;
	}
	public void setNavn(String navn) {
		this.navn = navn;
	}
	public OvelseGruppe getOvelsegruppe() {
		return ovelsegruppe;
	}
	public void setOverlsegruppe(OvelseGruppe ovelsegruppe) {
		this.ovelsegruppe = ovelsegruppe;
	}
	
}
