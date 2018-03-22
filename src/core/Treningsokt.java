package core;

import java.util.ArrayList;
import java.util.List;

public class Treningsokt {
	
	int treningsoktID; 
	String date; // "yyyy-mm-dd hh-mm-ss"
	int varighet;
	Notat notat;
	String notatTekst;
	List<Ovelse> ovelser = new ArrayList<>();
	
	
	public Treningsokt(int treningsoktID, String date, int varighet, Notat notat) {
		super();
		this.treningsoktID = treningsoktID;
		this.date = date;
		this.varighet = varighet;
		this.notat = notat;
		this.notatTekst = notat.getOpplevelse();
		
	}
	
	public String getNotatTekst() {
		return this.notatTekst;
	}
	
	public List<Ovelse> getOvelser(){
		return ovelser;
	}
	
	public void addOvelse(Ovelse o) {
		if (!ovelser.contains(o)) {
			ovelser.add(o);
		}
	}

	public int getTreningsoktID() {
		return treningsoktID;
	}

	public void setTreningsoktID(int treningsoktID) {
		this.treningsoktID = treningsoktID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getVarighet() {
		return varighet;
	}

	public void setVarighet(int varighet) {
		this.varighet = varighet;
	}

	public Notat getNotat() {
		return notat;
	}

	public void setNotat(Notat notat) {
		this.notat = notat;
	}
	
}
