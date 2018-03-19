package ui;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddExerciseController {
	
	private TrainingSession trainingSession;
	// Refererer til Main
	private Main main;
	
	DBConnector db = new DBConnector();
	
	@FXML
	private ComboBox<String> cmbExercises;
	
	@FXML
	private TextField comment;	
	
	@FXML
	private Button btnAnotherOne;
	
	@FXML
	private Button btnDone;
	
	@FXML
	private Button btnCancel;
	
	@FXML
	private TextField personligForm;
	
	private List<String> exerciseList = new ArrayList<String>();
	
	private List<String> resultList = new ArrayList<String>();
	
	public void setMain(Main main){
		this.main = main;
	}
	
	public void init(TrainingSession session){
		trainingSession = session;
		System.out.println("Gets here");
		FillComboBox();
	}
	public void cancel(){
		main.showVelkommen();
	}
	
	public void FillComboBox(){
		System.out.println("Kj�rer FillComboBox");
		List<String> exercises = db.getExerciseList(); // Need method for adding exercises
		System.out.println("etter liste-laging");
		System.out.println(exercises);
		cmbExercises.getItems().clear();
		cmbExercises.setValue(exercises.get(0));
		System.out.println("f�r for-l�kke");
		for(String exercise: exercises){
			cmbExercises.getItems().add(exercise);
		}
		System.out.println("etter for-l�kke");
	}
	
	@FXML
	public void anotherExercise(){
		System.out.println(cmbExercises.getValue() + "value");
		exerciseList.add((String) cmbExercises.getValue());
		resultList.add(comment.getText().toString());
		comment.setText("");
	}
	@FXML
	public void sendInSession(){
		db.setTrainingSession(trainingSession.getDate(), trainingSession.getStartTime(), trainingSession.getDuration(), null, trainingSession.getShape(), null, null);
		for(int i=0; i<exerciseList.size(); i++){
			db.setExecutedExercise(exerciseList.get(i), trainingSession.getDate(), trainingSession.getStartTime(), resultList.get(i));
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText("Session was added!");
		alert.showAndWait();
		main.showVelkommen();
	}
	
}