package ui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import core.Apparat;
import core.OvelseGruppe;
import core.OvelseMedApparat;
import core.OvelseUtenApparat;
import core.QueryFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class NyOvelseController {

    @FXML
    private TextField txtFieldNavn, txtFieldApparat, txtFieldKilo, txtFieldReps, txtFieldBeskrivelse, txtFieldOvelse;

    @FXML
    private CheckBox checkBox;

    @FXML
    private Button btnAdd;
    
    QueryFactory qf;
    
    public NyOvelseController(Connection conn) throws SQLException {
    	qf = new QueryFactory(conn);
    }
   
    public void btnLeggTil(ActionEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	
    	System.out.println("Legg til pushed");
    	String navn = txtFieldNavn.getText();
    	String ovelsegr = txtFieldOvelse.getText();
    	OvelseGruppe og = new OvelseGruppe(ovelsegr); // maa sjekke at finnes i DB
    	String kilo = txtFieldKilo.getText(); 
		// String Sets = txtFieldSets.getText(); <--- maa legge til felt for sets
		String reps = txtFieldReps.getText();
		
		if (checkBox.isSelected() ){
			String apparat = txtFieldApparat.getText();
			Apparat apa = new Apparat(apparat,"penis"); // maa sjekke at finnes i DB
			OvelseMedApparat oma = new OvelseMedApparat(navn, og, apa, Arrays.asList(Integer.parseInt(kilo), 5, Integer.parseInt(reps)));
			// Oppretter ny ovelse i databasen
			qf.setOvelseMedApparat(oma);
			System.out.println("Med apparat!");
		}
		
		else {
			String beskrivelse = txtFieldBeskrivelse.getText();
			OvelseUtenApparat oua = new OvelseUtenApparat(navn, og, beskrivelse, Arrays.asList(Integer.parseInt(kilo), 5, Integer.parseInt(reps)));
			// Oppretter ny ovelse i databasen
			qf.setOvelseUtenApparat(oua);
		}
		

		

    }

}
