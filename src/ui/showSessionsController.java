package ui;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;


public class showSessionsController {
	
	@FXML
	private TextArea txtInfo;
	
	@FXML
	private ComboBox<String> cmbTrening;
	
	@FXML
	private Button tilbake;
	
	private DBConnector DB = new DBConnector();
	
	
	// Refererer til Main
	private Main main;
	/**
	 * @param main Main-instansen som instansierer denne og som inneholder metodene for � bytte til de andre viewene.
	 */
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void handleTilbake() {
		main.showVelkommen();
	}
	
	public void handleVelgTrening() {

		List<List<String>> returnList = new ArrayList<List<String>>();
		returnList = DB.getExecutedExercises(cmbTrening.getValue());
		List<String> exerciseList = returnList.get(0);
		List<String> resultList = returnList.get(1);
		System.out.println("asd");
		String txt = "";
		
		for(int i=0; i < exerciseList.size(); i++){
			txt += "�velse: "+ exerciseList.get(i)+ "\n"
					+"Resultat: "+ resultList.get(i)+"\n\n"; 
		}
		
		txtInfo.setText(txt);
	}
	
	public void init() {
		FillComboBox();
	}
	
	public void FillComboBox(){
		System.out.println("hey");
		List<String> category = DB.getTrainingSessionList(); // Need method for adding exercises
		cmbTrening.getItems().clear();
		cmbTrening.setValue("Kategori");
		for(String category1: category){
			cmbTrening.getItems().add(category1);
		}
	}
}
