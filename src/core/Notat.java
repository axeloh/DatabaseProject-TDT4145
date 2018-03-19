package core;

public class Notat {


int notatID;
String treningsformal;
String opplevelse;

	
	public Notat(int notatID, String treningsformal, String opplevelse) {
		this.notatID = notatID;
		this.treningsformal = treningsformal;
		this.opplevelse = opplevelse;
	}
	
	public int getNotatID() {
		return notatID;
	}
	
	public void setNotatID(int notatID) {
		this.notatID = notatID;
	}
	public String getTreningsformal() {
		return treningsformal;
	}
	public void setTreningsformal(String treningsformal) {
		this.treningsformal = treningsformal;
	}
	public String getOpplevelse() {
		return opplevelse;
	}
	public void setOpplevelse(String opplevelse) {
		this.opplevelse = opplevelse;
	}

}
