package core;

public class OvelseITreningsokt {
	
	Treningsokt okt ;
	Ovelse ovelse;
	int kilo ;
	int sett;
	int reps;
	
	public OvelseITreningsokt(Treningsokt okt, Ovelse ovelse, int kilo, int sett, int reps) {
		super();
		this.okt = okt;
		this.ovelse = ovelse;
		this.kilo = kilo;
		this.sett = sett;
		this.reps = reps;
	}

	public Treningsokt getOkt() {
		return okt;
	}

	public void setOkt(Treningsokt okt) {
		this.okt = okt;
	}

	public Ovelse getOvelse() {
		return ovelse;
	}

	public void setOvelse(Ovelse ovelse) {
		this.ovelse = ovelse;
	}

	public int getKilo() {
		return kilo;
	}

	public void setKilo(int kilo) {
		this.kilo = kilo;
	}

	public int getSett() {
		return sett;
	}

	public void setSett(int sett) {
		this.sett = sett;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}
	
	
	
	

}
