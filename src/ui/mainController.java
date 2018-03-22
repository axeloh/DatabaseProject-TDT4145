package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    		
    	public OvelseCell(String ovelse) {
    		this.ovelse = ovelse;
    		
    		}
		public String getOvelse() {
			return ovelse;
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
	
	
	public void initialize() throws SQLException {
    	ObservableList<String> ovelseGruppeChoiceList = FXCollections.observableArrayList();
    	QueryFactory query = new QueryFactory( this.conn );
    	//må hente alle ovelsesgrupper i db til en liste  
 
    	
    	//bare for testing
    	ovelseGruppeChoiceList.add("Alle øvelser");
    	ovelseGruppeChoiceList.add("Skuldre");
    	ovelseGruppeChoiceList.add("Armer");
    	
    	
    	seOvelserTAB_choiceOvelsesgruppe.setItems(ovelseGruppeChoiceList);
    	
    	
    	//se resultatlogg-choicebox
    //	seResultatloggTAB_choiceOvelser
    List<Ovelse> ovelser =	query.getAlleOvelser();
	ObservableList<String> resultatloggChoiceList = FXCollections.observableArrayList();
	System.out.println(ovelser);
	for (Ovelse ov : ovelser ) {
		resultatloggChoiceList.add(ov.getNavn());
	}
	seResultatloggTAB_choiceOvelser.setItems(resultatloggChoiceList);
	
	
	}
	
	
    //*********nyOvelsegruppe-TAB***********
    //**************************************
    //*************************************
	
	
	
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
    	    	
        		OvelseGruppe ovelseGruppe = new OvelseGruppe(gruppeNavn);
    			query.setOvelseGruppe(ovelseGruppe);
    			
    		} catch (Exception e) {
    			nyOvelsesgruppeTAB_txtFeil.setText("Ugyldige verdier, prøv igjen.");
    			
    		}
    		
    		System.out.println("jkhsdf");
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
    
    
    
    public void nyttApparatTAB_btnLeggTil(ActionEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	System.out.println("legger til apparat");
    	nyttApparatTAB_txtFeil.setText("");
    	QueryFactory query = new QueryFactory( this.conn );	
    	

 
    	
		try {
	    	String navn = this.nyttApparatTAB_txtFieldNavn.getText();
	    	String beskrivelse = this.nyttApparatTAB_txtFieldBeskrivelse.getText();
	    	Apparat apparat = new Apparat(navn, beskrivelse);
	    	query.setApparat(apparat);
			
		} catch (Exception e) {
			nyttApparatTAB_txtFeil.setText("Ugyldige verdier, prøv igjen.");
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
    
    
    	//sjekk om apparat eksisterer
//    	try {
//    		apparat = query.getApparat(apparatnavn);
//    
//    		
//    	} catch (Exception e) {
//    		System.out.println("fant ikke apparat");
//    		
//    	}
    	
    	//sjekk om ovelsesgruppe eksisterer
    	//query.getOvelsesgruppe
    	
		try {
	    	String navn = this.nyOvelseTAB_txtFieldNavnMedApparat.getText();
	    	String apparatnavn = this.nyOvelseTAB_txtFieldApparatnavn.getText();
	    	String ovelsegruppe = this.nyOvelseTAB_txtFieldOvelsesgruppeNavnMedApparat.getText();
	    	
	  
	    	
	    	//for testing, kan eventuelt ha sjekk for om det eksisterer
	    	Apparat apparat = new Apparat(apparatnavn, "for kulinger");
	    	OvelseGruppe ovelsesgruppe = new OvelseGruppe(ovelsegruppe);
	    	OvelseMedApparat ovelse = new OvelseMedApparat(navn, ovelsesgruppe, apparat);
	    	
	    	
	    	//push to database
	    	query.setOvelseMedApparat(ovelse);
			
		} catch (Exception e) {
			nyOvelseTAB_txtFeil.setText("Ugyldige verdier, prøv igjen.");
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
    
 
    public void nyOvelseTAB_btnLeggTilUtenApparat(ActionEvent event) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    	QueryFactory query = new QueryFactory( this.conn );
    	nyOvelseTAB_txtFeil.setText("");
        
    	
    	try {
        	String navn = this.nyOvelseTAB_txtFieldNavnUtenApparat.getText();
        	String ovelseGruppe = this.nyOvelseTAB_txtFieldOvelsesgruppeNavnUtenApparat.getText();
        	String besk = this.nyOvelseTAB_txtAreaBeskrivelse.getText();
        	
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
    
    
    //må legge til funksjonalitet for denne 
//    @FXML
//    private TableColumn<OvelseCell, Integer> seOvelserTAB_ovelseCountColumn;

    @FXML
    private Button seOvelserTAB_btnSeOvelse;

    
    public void seOvelserTAB_btnSeOvelse(ActionEvent event) {
    	System.out.println("ser ovelser");
    	
    String valgt = seOvelserTAB_choiceOvelsesgruppe.getSelectionModel().getSelectedItem();
    
  
    if (valgt.equals("alle")) {
    	// se alle øvelser med count
    }
  
    //_______
    
    //setter liste
    ObservableList<OvelseCell> obs = FXCollections.observableArrayList();
    
    //må få liste fra db
 	//List<Ovelse> ovelser = db.getOvelser(Ovelsegruppe);
 	// for (Ovelse ov : overlser) { obs.add(  new OvelseCell( ov.getNavn() )  )  };
    
    
    //må fjerne denne, bare for testing
    obs.add( new OvelseCell("Hangups")  );
    obs.add(new OvelseCell("Pushdown"));
    
    //setter column
    seOvelserTAB_ovelseColumn.setCellValueFactory(new PropertyValueFactory<OvelseCell, String>("ovelse"));
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
    private Button seResultatloggTAB_btnVisResultatlogg;
    
    @FXML
    private Text seResultatloggTAB_txtFeil;
    
    public void seResultatloggTAB_btnVisResultatlogg(ActionEvent event) throws Exception {
    	QueryFactory query = new QueryFactory( this.conn );
    	seResultatloggTAB_txtFeil.setText("");

     
    	System.out.println("ser resultatlogg");
    	
      	try {
        	
        	//stor eller liten bokstav
        	String valgt2 = seResultatloggTAB_choiceOvelser.getSelectionModel().getSelectedItem();
        	
        	
        String start = this.seResultatloggTAB_txtFieldFraTid.getText();
        	String slutt = this.seResultatloggTAB_txtFieldTilTid.getText();
        	
//        	______input ___
        	System.out.println(valgt2);
        	System.out.println(start);
        	System.out.println(slutt);
        //	______input ferdig___
        	List<Ovelse> ovelser = query.getResultatLogg(valgt2, start, slutt);
        	
        	
         //setter liste
         ObservableList<LoggCell> obs2 = FXCollections.observableArrayList();
         
         System.out.println("ovelse.liste");
         System.out.println(ovelser);
         System.out.println("");
         for (Ovelse ov : ovelser ) {
        	 obs2.add( new LoggCell(ov.getNavn(), ov.getKilo(), ov.getReps(), ov.getSets())  );
        	 System.out.println("___________-");
        	 System.out.println(ov.getNavn());
        	 System.out.println(ov.getKilo());
        	 System.out.println(ov.getReps());
        	 System.out.println(ov.getSets());
        	 System.out.println("________");
         }
        	
         //setter column
         seResultatloggTAB_OvelseNavn.setCellValueFactory(new PropertyValueFactory<LoggCell, String>("navn"));
         
         seResultatloggTAB_OvelseKilo.setCellValueFactory(new PropertyValueFactory<LoggCell, Integer>("kilo"));
         seResultatloggTAB_OvelseReps.setCellValueFactory(new PropertyValueFactory<LoggCell, Integer>("reps"));
         seResultatloggTAB_OvelseSet.setCellValueFactory(new PropertyValueFactory<LoggCell, Integer>("set"));
         
         seResultatloggTAB_tableViewResultatlogg.setItems(obs2);

		} catch (Exception e) {
			seResultatloggTAB_txtFeil.setText("Ugyldige verdier, prøv igjen.");
		}
      	
    	
    	
    }
    
  
 
    

}
