package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import core.Ovelse;
import core.Treningsokt;

public class mainController {
	
    //*********nyOvelsegruppe-TAB***********
    //**************************************
    //**************************************

    @FXML
    private TextField nyOvelsesgruppeTAB_txtFieldNavn;
    
    @FXML
    private Button nyOvelsesgruppeTAB_btnAdd;

    
    public void nyOvelsesgruppeTAB_btnLeggTil(ActionEvent event) {
    	System.out.println("legger til øvelsesgruppe");
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
    
    
    public void nyttApparatTAB_btnLeggTil(ActionEvent event) {
    	System.out.println("legger til apparat");
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
    
    
    public void nyOvelseTAB_btnLeggTilMedApparat(ActionEvent event) {
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
    
 
    public void nyOvelseTAB_btnLeggTilUtenApparat(ActionEvent event) {
    	System.out.println("legger til ovelse uten apparat");
    }
    
	//***********nyTreningsokt-TAB******
	//**********************************
	//**********************************
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

    
    public void nyTreningsoktTAB_btnAddOkt(ActionEvent event) {
    	System.out.println("legger til treningsøkt");
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

    
    public void nyTreningsoktTAB_btnAddOvelseIOkt(ActionEvent event) {
    	System.out.println("legger til øvelse for økt");
    }
    
    
    //***********seOvelser-TAB**************
    //**************************************
    //**************************************
    
  
  
    @FXML
    private ChoiceBox<String> seOvelserTAB_choiceOvelsesgruppe;
    @FXML
    private TableView<Ovelse> seOvelserTAB_tableViewOvelser;
    @FXML
    private TableColumn<Ovelse, String> seOvelserTAB_ovelseColumn;

    @FXML
    private Button seOvelserTAB_btnSeOvelse;

    
    public void seOvelserTAB_btnSeOvelse(ActionEvent event) {
    	System.out.println("ser ovelser");
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
