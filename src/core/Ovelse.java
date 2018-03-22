package core;

public abstract class Ovelse {

	String navn;
	OvelseGruppe ovelsegruppe;
	int kilo;
	int sets;
	int reps;
	
	public int getKilo() {
		return kilo;
	}
	public void setKilo(int kilo) {
		this.kilo = kilo;
	}
	public int getSets() {
		return sets;
	}
	public void setSets(int sets) {
		this.sets = sets;
	}
	public int getReps() {
		return reps;
	}
	public void setReps(int reps) {
		this.reps = reps;
	}
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
