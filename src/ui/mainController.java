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
	//__________________________
	
	Connection conn = DBConnection.getDBConnection();
	
	
	public void initialize() throws SQLException {
    	ObservableList<String> ovelseGruppeChoiceList = FXCollections.observableArrayList();
    	
    	//må hente alle ovelsesgrupper i db til en liste    
    	
    	//bare for testing
    	ovelseGruppeChoiceList.add("Skuldre");
    	ovelseGruppeChoiceList.add("Armer");
    	
    	
    	seOvelserTAB_choiceOvelsesgruppe.setItems(ovelseGruppeChoiceList);
	}
	
	
    //*********nyOvelsegruppe-TAB***********
    //**************************************
    //**************************************
	
	
	
    @FXML
    private TextField nyOvelsesgruppeTAB_txtFieldNavn;
    
    @FXML
    private Button nyOvelsesgruppeTAB_btnAdd;

    
    public void nyOvelsesgruppeTAB_btnLeggTil(ActionEvent event) throws Exception {
    	QueryFactory query = new QueryFactory( this.conn );	
    	
    		String gruppeNavn = this.nyOvelsesgruppeTAB_txtFieldNavn.getText();
    	
    		OvelseGruppe ovelseGruppe = new OvelseGruppe(gruppeNavn);
   
    		query.setOvelseGruppe(ovelseGruppe);
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
    
    
    public void nyttApparatTAB_btnLeggTil(ActionEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	System.out.println("legger til apparat");
    	QueryFactory query = new QueryFactory( this.conn );	
    	
    	String navn = this.nyttApparatTAB_txtFieldNavn.getText();
    	String beskrivelse = this.nyttApparatTAB_txtFieldBeskrivelse.getText();
    	Apparat apparat = new Apparat(navn, beskrivelse);
    	query.setApparat(apparat);
    	
    	System.out.println();
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
    
    
    public void nyOvelseTAB_btnLeggTilMedApparat(ActionEvent event) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    	QueryFactory query = new QueryFactory( this.conn );	
    	
    	String navn = this.nyOvelseTAB_txtFieldNavnMedApparat.getText();
    	String apparatnavn = this.nyOvelseTAB_txtFieldApparatnavn.getText();
    	String ovelsegruppe = this.nyOvelseTAB_txtFieldOvelsesgruppeNavnMedApparat.getText();
    	
    	Apparat apparat = new Apparat("Stativ", "for kulinger");
    	OvelseGruppe ovelse1 = new OvelseGruppe("Armer");
    
    	
    	OvelseMedApparat ovelse = new OvelseMedApparat(navn, ovelse1, apparat);
    	query.setOvelseMedApparat(ovelse);
    	
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
    	String navn = this.nyOvelseTAB_txtFieldNavnUtenApparat.getText();
    	String ovelseGruppe = this.nyOvelseTAB_txtFieldOvelsesgruppeNavnUtenApparat.getText();
    	String besk = this.nyOvelseTAB_txtAreaBeskrivelse.getText();
    	
    	OvelseGruppe ovelseGrp = new OvelseGruppe("Armer");
    	
    	OvelseUtenApparat ovelse = new OvelseUtenApparat(navn, ovelseGrp, besk);
    	query.setOvelseUtenApparat(ovelse);
    	
    	
    	
    	
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

    
    public void nyTreningsoktTAB_btnAddOkt(ActionEvent event) throws SQLException {
    	System.out.println("legger til treningsøkt");
    	QueryFactory query = new QueryFactory( this.conn );
    	
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
    	String navn = this.nyTreningsoktTAB_txtFieldNavn.getText();
    	int kilo = Integer.parseInt(this.nyTreningsoktTAB_txtFieldKilo.getText()); 
    	int reps = Integer.parseInt(this.nyTreningsoktTAB_txtFieldReps.getText()); 
    	int set = Integer.parseInt(this.nyTreningsoktTAB_txtFieldSet.getText()); 
    	
    	List<Integer> performance = new ArrayList<Integer>();
    	performance.add(kilo);
    	performance.add(reps);
    	performance.add(set);
    	
    	//hente fra db, sjekk om apparat eller ikke
    	if (true) {
    		//if apparat

    		
    		
    		//hent ovelsesgruppen til ovelsen
    		//Ovelsesgruppe gruppe = db.getOvelsesgruppe(Ovelse ovelse);
    		//må gjøre samme for apparat
    		
    		//opprettet midlertidlig
    		OvelseGruppe gruppe = new OvelseGruppe("Armer");
    		Apparat apparat = new Apparat("Stativ", "test");
    		
    		
    		OvelseMedApparat ovelse = new OvelseMedApparat(navn,gruppe, apparat, performance);
    		
    		query.setOvelseITreningsokt(this.okt, ovelse);
    	} else {
      //if not apparat


    		//hent ovelsesgruppen til ovelsen
    		//Ovelsesgruppe gruppe = db.getOvelsesgruppe(Ovelse ovelse);
    		//må gjøre samme for apparat
    		
    		//opprettet midlertidlig
    		OvelseGruppe gruppe = new OvelseGruppe("Armer");
    		
    		
    		OvelseUtenApparat ovelse = new OvelseUtenApparat(navn,gruppe, "", performance);
    		
    		query.setOvelseITreningsokt(this.okt, ovelse);
    		
    	}
    	
    }
    
    
    //***********seOvelser-TAB**************
    //**************************************
    //**************************************
    
  
  
    @FXML
    private ChoiceBox<String> seOvelserTAB_choiceOvelsesgruppe;
    @FXML
    private TableView<OvelseCell> seOvelserTAB_tableViewOvelser;
    @FXML
    private TableColumn<OvelseCell, String> seOvelserTAB_ovelseColumn;

    @FXML
    private Button seOvelserTAB_btnSeOvelse;

    
    public void seOvelserTAB_btnSeOvelse(ActionEvent event) {
    	System.out.println("ser ovelser");
    	
    String valgt = seOvelserTAB_choiceOvelsesgruppe.getSelectionModel().getSelectedItem();
    
  
  
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
    private TableColumn<Treningsokt, String> seTreningsokterTAB_TreningsIDColumn;
    @FXML
    private TableColumn<Treningsokt, String> seTreningsokterTAB_DatoColumn;
    @FXML
    private TableColumn<Treningsokt, Integer> seTreningsokterTAB_TidColumn;
    @FXML
    private TableColumn<Treningsokt, String> seTreningsokterTAB_VarighetColumn;
    @FXML
    private TableColumn<Treningsokt, String> seTreningsokterTAB_NotatColumn;
    
    
    @FXML
    private Button seTreningsokterTAB_btnVisTreninger;

    
    public void seTreningsokterTAB_btnVisTreninger(ActionEvent event) {
    	System.out.println("ser treningokterr");
    }
    
    
    
    

    
    
 
    

}
