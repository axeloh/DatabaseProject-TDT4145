package first_package;

public class OvelseMedApparat extends Ovelse{
	
	Apparat apparat;
	
	public OvelseMedApparat(String navn, OvelseGruppe ovelsegruppe, Apparat apparat) {
		this.navn = navn;
		this.ovelsegruppe = ovelsegruppe;
		this.apparat = apparat;
	}

	public Apparat getApparatnavn() {
		return apparat;
	}
	
	public void setApparatnavn(Apparat apparatnavn) {
		this.apparat = apparatnavn;
	}
	
	


}
