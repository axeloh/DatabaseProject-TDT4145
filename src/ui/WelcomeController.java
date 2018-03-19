package ui;

import core.Apparat;
import core.OvelseGruppe;
import core.OvelseMedApparat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class WelcomeController {

    @FXML
    private TextField txtFieldNavn;

    @FXML
    private TextField txtFieldApparat;

    @FXML
    private TextField txtFieldKilo;

    @FXML
    private TextField txtFieldReps;

    @FXML
    private TextField txtFieldBeskrivelse;
    
    @FXML
    private TextField txtFieldOvelse;

    @FXML
    private CheckBox checkBox;

    @FXML
    private Button btnAdd;
    
   
    public void btnLeggTil(ActionEvent event) {
    	
    	System.out.println("Legg til pushed");
    	String Navn = txtFieldNavn.getText();
    	String ovelsegr = txtFieldOvelse.getText();
		
		
		if (checkBox.isSelected() ){
			String aparat = txtFieldApparat.getText();
			Apparat apa = new Apparat(aparat,"penis"); // må hente ut fra DB
			String Kilo = txtFieldKilo.getText();
			String Reps = txtFieldReps.getText();
			OvelseGruppe og = new OvelseGruppe(ovelsegr);
			OvelseMedApparat oma = new  OvelseMedApparat(Navn,og,apa);

			System.out.println("Med apparat!");
		}
		
		else {
			String Beskrivelse = txtFieldBeskrivelse.getText();
		}

    }

}
