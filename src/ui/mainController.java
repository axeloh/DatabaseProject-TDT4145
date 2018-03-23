package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.management.Query;

import core.Apparat;
import core.DBConnection;
import core.Notat;
import core.Ovelse;
import core.OvelseGruppe;
import core.OvelseMedApparat;
import core.OvelseUtenApparat;
import core.QueryFactory;
import core.Treningsokt;

public class mainController {
	
	  //_____CLASS
    public class OvelseCell {
    		private String ovelse;
    		private int antall;
    		
    	public OvelseCell(String ovelse,int antall) {
    		this.ovelse = ovelse;
    		this.antall = antall;
    		
    		}
		public String getOvelse() {
			return this.ovelse;
		}
		
		public int getAntall() {
			return this.antall;
		}

    	
    	
    }
    
    public class LoggCell {
		private String navn;
		private int kilo, reps, set;
		
	public LoggCell(String navn,int kilo,int reps,int set) {
		this.navn = navn;
		this.kilo = kilo;
		this.reps = reps;
		this.set = set;
		
		}

	public String getNavn() {
		return navn;
	}

	public int getKilo() {
		return kilo;
	}

	public int getReps() {
		return reps;
	}

	public int getSet() {
		return set;
	}
	
	

	
	
}
	//__________________________
	
	Connection conn = DBConnection.getDBConnection();
	
	List<OvelseGruppe> alleOvGruppe;
	List<Ovelse> ovelser;
	List<Apparat> apparater;
	List<Treningsokt> okter;
	
	
	
	public void update() throws SQLException {
		
		// Se mulige ovelsesgrupper i SeOvelse-tab
		QueryFactory query = new QueryFactory( this.conn );
		ObservableList<String> ovelseGruppeChoiceList = FXCollections.observableArrayList();
		alleOvGruppe = query.getAlleOvelsegrupper();
		ovelseGruppeChoiceList.add("Alle ovelser");
    	for (OvelseGruppe gr : alleOvGruppe) {
    		ovelseGruppeChoiceList.add(gr.getNavn());
    	}
    	seOvelserTAB_choiceOvelsesgruppe.setItems(ovelseGruppeChoiceList);
    	
    	// Se allerede registrerte ovelsesgrupper i nyOvelsegruppe-tab
    	ovelseGruppeChoiceList.remove(0);
    	ovelsesgruppe_lw.setItems(ovelseGruppeChoiceList);
    	
    	//Se ovelse-choicebox i resultatlogg-tab
        ovelser =	query.getAlleOvelser();
    	ObservableList<String> resultatloggChoiceList = FXCollections.observableArrayList();
    	for (Ovelse ov : ovelser ) {
    		resultatloggChoiceList.add(ov.getNavn());
    	}
    	seResultatloggTAB_choiceOvelser.setItems(resultatloggChoiceList);
    	
    	
		
    	// Se allerede registrerte apparater i apparat-tab
    	ObservableList<String> apparatList = FXCollections.observableArrayList();
    	apparater = query.getAlleApparater();
    	apparater.stream().forEach(a -> apparatList.add(a.getNavn()));
    	apparater_lw.setItems(apparatList);
    	
    	
	}
	
	public void initialize() throws SQLException {
    	update();

    
	
	}
	
	
    //*********nyOvelsegruppe-TAB***********
    //**************************************
    //*************************************
	
	@FXML
	private ListView<String> ovelsesgruppe_lw;
	
    @FXML
    private TextField nyOvelsesgruppeTAB_txtFieldNavn;
    
    @FXML
    private Button nyOvelsesgruppeTAB_btnAdd;
    
    @FXML
    private Text nyOvelsesgruppeTAB_txtFeil;
    


    
    public void nyOvelsesgruppeTAB_btnLeggTil(ActionEvent event) throws Exception {
    	QueryFactory query = new QueryFactory( this.conn );	
    	nyOvelsesgruppeTAB_txtFeil.setText("");
   
    		try {
    			String gruppeNavn = this.nyOvelsesgruppeTAB_txtFieldNavn.getText();
    	    	boolean finnes = alleOvGruppe.stream().anyMatch(og -> og.getNavn().equals(gruppeNavn));
    	    	if (finnes) {
    	    		nyOvelsesgruppeTAB_txtFeil.setText("�velsegruppen er allerede registrert.");
    	    	}
    	    	else {
    	    		OvelseGruppe ovelseGruppe = new OvelseGruppe(gruppeNavn);
        			query.setOvelseGruppe(ovelseGruppe);
        			nyOvelsesgruppeTAB_txtFeil.setText(gruppeNavn + " lagt til.");
            		update();
    	    	}
        		
    			
    		} catch (Exception e) {
    			nyOvelsesgruppeTAB_txtFeil.setText("Noe feil skjedde, pr�v igjen.");
    			
    		}
    }
    
    
    //************nyttApparat-TAB***********
    //**************************************
    //**************************************
    
    @FXML
    private TextField nyttApparatTAB_txtFieldNavn;
    
    @FXML
    private TextField nyttApparatTAB_txtFieldBeskrivelse;
    
    @FXML
    private Button nyttApparatTAB_btnAdd;
    
    @FXML
    private Text nyttApparatTAB_txtFeil;
    
    @FXML
    private ListView<String> apparater_lw;
    
    
    
    public void nyttApparatTAB_btnLeggTil(ActionEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	System.out.println("legger til apparat");
    	nyttApparatTAB_txtFeil.setText("");
    	QueryFactory query = new QueryFactory( this.conn );	

		try {
	    	String navn = this.nyttApparatTAB_txtFieldNavn.getText();
	    	String beskrivelse = this.nyttApparatTAB_txtFieldBeskrivelse.getText();
	    	if (apparater.stream().anyMatch(a -> a.getNavn().equals(navn))) {
	    		nyttApparatTAB_txtFeil.setText("Apparatet er allerede registrert.");
	    		
	    	}
	    	else {
	    		Apparat apparat = new Apparat(navn, beskrivelse);
		    	query.setApparat(apparat);
		    	nyttApparatTAB_txtFeil.setText("Apparat lagt til.");
				update();
	    	}

		} catch (Exception e) {
			nyttApparatTAB_txtFeil.setText("Noe feil skjedde, pr�v igjen.");
		}
		

    }
    
    
    
    
    //***********nyOvelse-TAB**********
    //**************************************
    //**************************************
    
    //med apparat
    @FXML
    private TextField nyOvelseTAB_txtFieldNavnMedApparat;
    @FXML
    private TextField nyOvelseTAB_txtFieldApparatnavn;
    @FXML
    private TextField nyOvelseTAB_txtFieldOvelsesgruppeNavnMedApparat;
    @FXML
    private Button nyOvelseTAB_btnAddMedApparat;
    
    @FXML
    private Text nyOvelseTAB_txtFeil;
    
    
    public void nyOvelseTAB_btnLeggTilMedApparat(ActionEvent event) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    	QueryFactory query = new QueryFactory( this.conn );	
    	
    	nyOvelseTAB_txtFeil.setText("");
    	
		try {
	    	String navn = this.nyOvelseTAB_txtFieldNavnMedApparat.getText();
	    	String apparatnavn = this.nyOvelseTAB_txtFieldApparatnavn.getText();
	    	String ovelsegruppe = this.nyOvelseTAB_txtFieldOvelsesgruppeNavnMedApparat.getText();
	    	
	    	// Sjekk om apparat eksisterer
	    	if (!(apparater.stream().anyMatch(a -> a.getNavn().equals(apparatnavn)))){
	    		nyOvelseTAB_txtFeil.setText("Apparatet er ikke registrert. Registrer det f�rst, og prov igjen.");
	    	}
	    	
	    	// Sjekk om ovelsegruppe eksisterer
	    	else if (!(alleOvGruppe.stream().anyMatch(og -> og.getNavn().equals(ovelsegruppe)))) {
	    		nyOvelseTAB_txtFeil.setText("Ovelsegruppen er ikke registrert. Registrer det f�rst, og prov igjen.");
	    	}
	    	
	    	else {
	    		Apparat apparat = new Apparat(apparatnavn, "for kulinger");
		    	OvelseGruppe ovelsesgruppe = new OvelseGruppe(ovelsegruppe);
		    	OvelseMedApparat ovelse = new OvelseMedApparat(navn, ovelsesgruppe, apparat);
		    	//push to database
		    	query.setOvelseMedApparat(ovelse);
		    	update();
	    	}
	    	
		} catch (Exception e) {
			nyOvelseTAB_txtFeil.setText("Noe feil skjedde, prov igjen.");
		}
    	
    	System.out.println("legger til ovelse med apparat");
    }
    
    
    //________
    //uten apparat
    @FXML
    private TextField nyOvelseTAB_txtFieldNavnUtenApparat;
    @FXML
    private TextField nyOvelseTAB_txtFieldOvelsesgruppeNavnUtenApparat;
    @FXML
    private TextArea nyOvelseTAB_txtAreaBeskrivelse;
    @FXML
    private Button nyOvelseTAB_btnAddUtenApparat;
    @FXML
    private TextField nyOvelseTAB_txtFieldOvelsesgruppeBeskrivelseUtenApparat;
    
 
    public void nyOvelseTAB_btnLeggTilUtenApparat(ActionEvent event) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    	QueryFactory query = new QueryFactory( this.conn );
    	nyOvelseTAB_txtFeil.setText("");
        
    	
    	try {
        	String navn = this.nyOvelseTAB_txtFieldNavnUtenApparat.getText();
        	String ovelseGruppe = this.nyOvelseTAB_txtFieldOvelsesgruppeNavnUtenApparat.getText();
        	String besk = this.nyOvelseTAB_txtFieldOvelsesgruppeBeskrivelseUtenApparat.getText();
        	System.out.println("beskrivelse:");
        	System.out.println(besk);
        	
        	//eventuelt sjekk for om denne eksisterer
        	OvelseGruppe ovelseGrp = new OvelseGruppe(ovelseGruppe);
        	
        	OvelseUtenApparat ovelse = new OvelseUtenApparat(navn, ovelseGrp, besk);
        	
        	//push to database
        	query.setOvelseUtenApparat(ovelse);
        	
			
		} catch (Exception e) {
			nyOvelseTAB_txtFeil.setText("Ugyldige verdier, prøv igjen.");
		}
    	
    	
    	
    	
    	System.out.println("legger til ovelse uten apparat");
    }
    
	//***********nyTreningsokt-TAB******
	//**********************************
	//**********************************
    Treningsokt okt;
    
    @FXML
    private TextField nyTreningsoktTAB_txtFieldID;
    @FXML
    private TextField nyTreningsoktTAB_txtFieldDato;
    @FXML
    private TextField nyTreningsoktTAB_txtFieldVarighet;
    @FXML
    private TextField nyTreningsoktTAB_txtFieldNotatID;
    @FXML
    private TextField nyTreningsoktTAB_txtFieldFormal;
    @FXML
    private TextField nyTreningsoktTAB_txtFieldOpplevelse;
    @FXML
    private Button nyTreningsoktTAB_btnAddOkt;
    
    @FXML
    private Text nyTreningsoktTAB_txtFeil;
    
    public void nyTreningsoktTAB_btnAddOkt(ActionEvent event) throws SQLException {
    	System.out.println("legger til treningsøkt");
    	QueryFactory query = new QueryFactory( this.conn );
    	nyTreningsoktTAB_txtFeil.setText("");

    	
       	try {
        	int ID = Integer.parseInt(this.nyTreningsoktTAB_txtFieldID.getText());
        	String dato = this.nyTreningsoktTAB_txtFieldDato.getText();
        	int varighet = Integer.parseInt(this.nyTreningsoktTAB_txtFieldVarighet.getText());
        	int notatID = Integer.parseInt(this.nyTreningsoktTAB_txtFieldNotatID.getText());
        	String formal = this.nyTreningsoktTAB_txtFieldFormal.getText();
        	String opplevelse = this.nyTreningsoktTAB_txtFieldOpplevelse.getText();

        	Notat notat = new Notat(notatID, formal, opplevelse);
        	this.okt = new Treningsokt(ID, dato, varighet, notat);
        	
        	
       	query.setNotat(notat);
        	query.setTreningsokt(okt);
			
		} catch (Exception e) {
			nyTreningsoktTAB_txtFeil.setText("Ugyldige verdier, prøv igjen.");
		}
    	
    	
  
  
    
    	
    	
   
    	
    	
    	
    	
    	
    }
    
    //______
    //ovelser
    @FXML
    private TextField nyTreningsoktTAB_txtFieldNavn;
    @FXML
    private TextField nyTreningsoktTAB_txtFieldKilo;
    @FXML
    private TextField nyTreningsoktTAB_txtFieldReps;
    @FXML
    private TextField nyTreningsoktTAB_txtFieldSet;
    
    @FXML
    private Button nyTreningsoktTAB_btnAddOvelseIOkt;

    
    public void nyTreningsoktTAB_btnAddOvelseIOkt(ActionEvent event) throws SQLException {
	    	System.out.println("legger til øvelse for økt");
	    	QueryFactory query = new QueryFactory( this.conn );
	    	nyTreningsoktTAB_txtFeil.setText("");
				
      	try {
	    	String navn = this.nyTreningsoktTAB_txtFieldNavn.getText();
	    	int kilo = Integer.parseInt(this.nyTreningsoktTAB_txtFieldKilo.getText()); 
	    	int reps = Integer.parseInt(this.nyTreningsoktTAB_txtFieldReps.getText()); 
	    	int set = Integer.parseInt(this.nyTreningsoktTAB_txtFieldSet.getText()); 
	    	
	    	List<Integer> performance = new ArrayList<Integer>();
	    	performance.add(kilo);
	    	performance.add(reps);
	    	performance.add(set);
	    	
	
	    		
		
		//unødvendig, kan vel være null
		Apparat apparat = new Apparat("Stativ", "test");
		OvelseGruppe gruppe = new OvelseGruppe("Armer");
		
		
		//denne kan brukes selv om ovelsen er uten apparat bruker bare superklasse-felter
		OvelseMedApparat ovelse = new OvelseMedApparat(navn,gruppe, apparat, performance);
		
		//push to db
		query.setOvelseITreningsokt(this.okt, ovelse);
		

		} catch (Exception e) {
			nyTreningsoktTAB_txtFeil.setText("Ugyldige verdier, prøv igjen.");
		}
    	
    	

    }
   
    
    
    //***********seOvelser-TAB**************
    //**************************************
    //**************************************
    
  //fix for count
  
    @FXML
    private ChoiceBox<String> seOvelserTAB_choiceOvelsesgruppe;
    @FXML
    private TableView<OvelseCell> seOvelserTAB_tableViewOvelser;
    @FXML
    private TableColumn<OvelseCell, String> seOvelserTAB_ovelseColumn;
    @FXML
    private TableColumn<OvelseCell, Integer> seOvelserTAB_ovelseAntallColumn;
    
    
    //må legge til funksjonalitet for denne 
//    @FXML
//    private TableColumn<OvelseCell, Integer> seOvelserTAB_ovelseCountColumn;

    @FXML
    private Button seOvelserTAB_btnSeOvelse;

    
    public void seOvelserTAB_btnSeOvelse(ActionEvent event) throws SQLException {
    	QueryFactory query = new QueryFactory( this.conn );
    	System.out.println("ser ovelser");
    	
    String valgt = seOvelserTAB_choiceOvelsesgruppe.getSelectionModel().getSelectedItem();
    
    List<Ovelse> ovelser;
   
    ObservableList<OvelseCell> obs = FXCollections.observableArrayList();
    
    if (valgt.equals("Alle øvelser")) {
    		ovelser = query.getAlleOvelser();
    	
    } else {
    		ovelser = query.getOvelserInOvelsegruppe(valgt);
    }

  
    for (Ovelse ov : ovelser ) {
    		OvelseCell ovCell = new OvelseCell(ov.getNavn(),query.getAntallGangerOvelseUtfort(ov.getNavn()));
 
    		obs.add(ovCell);

    }
    
   

    
    //setter column
    seOvelserTAB_ovelseColumn.setCellValueFactory(new PropertyValueFactory<OvelseCell, String>("ovelse"));
    seOvelserTAB_ovelseAntallColumn.setCellValueFactory(new PropertyValueFactory<OvelseCell, Integer>("antall"));

    seOvelserTAB_tableViewOvelser.setItems(obs);
 

    }
    
    
    
    
    //***********seTreningsokter-TAB****************
    //**************************************
    //**************************************
    @FXML
    private TextField seTreningsokterTAB_txtFieldAntallOkter;
    
  
    @FXML
    private TableView<Treningsokt> seTreningsokterTAB_tableViewOkter;
    @FXML
    private TableColumn<Treningsokt, Integer> seTreningsokterTAB_TreningsIDColumn;
    @FXML
    private TableColumn<Treningsokt, String> seTreningsokterTAB_DatoColumn;
 
    @FXML
    private TableColumn<Treningsokt, Integer> seTreningsokterTAB_VarighetColumn;
    @FXML
    private TableColumn<Treningsokt, String> seTreningsokterTAB_NotatColumn;
    
    
    @FXML
    private Button seTreningsokterTAB_btnVisTreninger;
    
    @FXML
    private Text seTreningsokterTAB_txtFeil;

    
    public void seTreningsokterTAB_btnVisTreninger(ActionEvent event) throws SQLException {
    	
    	QueryFactory query = new QueryFactory( this.conn );
    		///FIX!!
    	seTreningsokterTAB_txtFeil.setText("");
 
      	try {
      	int antall = Integer.parseInt(this.seTreningsokterTAB_txtFieldAntallOkter.getText());
        	List<Treningsokt> okter = query.getSisteTreningsokter(antall);
    		
            //setter liste
            ObservableList<Treningsokt> obs2 = FXCollections.observableArrayList();

            for (Treningsokt okt : okter ) {
           	 	obs2.add(okt);
           }
            
            this.seTreningsokterTAB_TreningsIDColumn.setCellValueFactory(new PropertyValueFactory<Treningsokt, Integer>("treningsoktID"));
            this.seTreningsokterTAB_DatoColumn.setCellValueFactory(new PropertyValueFactory<Treningsokt, String>("date"));
            this.seTreningsokterTAB_VarighetColumn.setCellValueFactory(new PropertyValueFactory<Treningsokt, Integer>("varighet"));
            this.seTreningsokterTAB_NotatColumn.setCellValueFactory(new PropertyValueFactory<Treningsokt, String>("notatTekst"));
        
            
            this.seTreningsokterTAB_tableViewOkter.setItems(obs2);

		} catch (Exception e) {
			seTreningsokterTAB_txtFeil.setText("Ugyldige verdier, prøv igjen.");
		}
    	
    	
    	
 
     
     
    }
    
    
    //***********seResultatlogg-TAB****************
    //**************************************
    //**************************************
    
    @FXML
    private ChoiceBox<String> seResultatloggTAB_choiceOvelser;
    
    @FXML
    private TableView<LoggCell> seResultatloggTAB_tableViewResultatlogg;
    @FXML
    private TableColumn<LoggCell, String> seResultatloggTAB_OvelseNavn;
    @FXML
    private TableColumn<LoggCell, Integer> seResultatloggTAB_OvelseKilo;
    @FXML
    private TableColumn<LoggCell, Integer> seResultatloggTAB_OvelseReps;
    @FXML
    private TableColumn<LoggCell, Integer> seResultatloggTAB_OvelseSet;
    
    @FXML
    private TextField seResultatloggTAB_txtFieldFraTid;
    @FXML
    private TextField seResultatloggTAB_txtFieldTilTid;
    
    @FXML
    private Text info;
    

    @FXML
    private Button seResultatloggTAB_btnVisResultatlogg;
    
    @FXML
    private Text seResultatloggTAB_txtFeil;
    
//    @FXML 
//    private ProgressBar progressbar;
    
    @FXML
    private ProgressBar progressbar;
    
    public void seResultatloggTAB_btnVisResultatlogg(ActionEvent event) throws Exception {
    	QueryFactory query = new QueryFactory( this.conn );
    	seResultatloggTAB_txtFeil.setText("");


    	System.out.println("ser resultatlogg");
    	
      	try {
        	
        	//stor eller liten bokstav
        	String valgt2 = seResultatloggTAB_choiceOvelser.getSelectionModel().getSelectedItem();
        	
        String start = this.seResultatloggTAB_txtFieldFraTid.getText();
        String slutt = this.seResultatloggTAB_txtFieldTilTid.getText();
        if (start.length() == 0) {
        	start = "0000-01-01 00:00:00";
        }
        if (slutt.length() == 0) {
        	slutt = "3000-01-01 00:00:00";
        }
        	
//        	______input ___
  
        //	______input ferdig___
        ObservableList<LoggCell> obs2 = FXCollections.observableArrayList();
        double i = 0.0;

    	if (valgt2 == null) {
    		for (Ovelse o : this.ovelser) {
    			
    	    	progressbar.setProgress(Double.parseDouble(Double.toString(i).substring(0,3)));
    			List<Ovelse> ovelser = query.getResultatLogg(o.getNavn(), start, slutt);
    			 for (Ovelse ov : ovelser ) {
    				 
    	        	 obs2.add( new LoggCell(ov.getNavn(), ov.getKilo(), ov.getReps(), ov.getSets())  );
    	         }
    			 i += 1/(double)this.ovelser.size();
    			 System.out.println(i);
    		}
    		
    	}
    	
    	else {
    		List<Ovelse> ovelser = query.getResultatLogg(valgt2, start, slutt);
    		for (Ovelse ov : ovelser ) {
           	 obs2.add( new LoggCell(ov.getNavn(), ov.getKilo(), ov.getReps(), ov.getSets())  );
    
            }
    	}

         //setter column
         seResultatloggTAB_OvelseNavn.setCellValueFactory(new PropertyValueFactory<LoggCell, String>("navn"));
         
         seResultatloggTAB_OvelseKilo.setCellValueFactory(new PropertyValueFactory<LoggCell, Integer>("kilo"));
         seResultatloggTAB_OvelseReps.setCellValueFactory(new PropertyValueFactory<LoggCell, Integer>("reps"));
         seResultatloggTAB_OvelseSet.setCellValueFactory(new PropertyValueFactory<LoggCell, Integer>("set"));
         
         seResultatloggTAB_tableViewResultatlogg.setItems(obs2);

		} catch (Exception e) {
			e.printStackTrace();
			seResultatloggTAB_txtFeil.setText("Ugyldige verdier, prøv igjen.");
		}
    }
    
    
  
 
    

}
