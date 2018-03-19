package ui;

import java.util.List;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class deleteExerciseController {
	@FXML
	private ComboBox<String> cmbDelete;
	
	@FXML
	private Button btnDelete;
	
	@FXML
	private Button btnCancel;
	
	DBConnector db = new DBConnector();
	
	public void init(){
		FillComboBox();
	}
	
	// Refererer til Main
	private Main main;
	/**
	 * @param main Main-instansen som instansierer denne og som inneholder metodene for � bytte til de andre viewene.
	 */
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void FillComboBox(){
		List<String> exercises = db.getExerciseList(); // Need method for adding exercises
		cmbDelete.getItems().clear();
		cmbDelete.setValue(exercises.get(0));
		for(String exercise: exercises){
			cmbDelete.getItems().add(exercise);
		}
		
	}
	@FXML
	public void cancel(){
		main.showVelkommen();
	}
	@FXML
	public void deleteObject(){
		String value = (String)cmbDelete.getValue();
		if(!db.exerciseInSession(value)){
			db.deleteExercise(value);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText(null);
			alert.setContentText("Exercise was deleted!");
			alert.showAndWait();
			main.showVelkommen();
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error");
			alert.setContentText("This exercise is in an earlier session and cannot be deleted!");

			alert.showAndWait();
		}
	}
}
