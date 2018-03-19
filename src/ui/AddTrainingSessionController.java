package ui;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
 
 public class AddTrainingSessionController {
	
 	private Main main;
 	private TrainingSession trainingSession;
 	
 	@FXML
 	DatePicker dato;
 	
 	@FXML
 	TextField tid;
 	
 	@FXML
 	TextField varighet;
 	
 	@FXML
 	TextField personligForm;
 	
 	@FXML
 	Button btnLegOv;
 	
 	@FXML
 	Button btnCancel;
 	
 	public void setMain(Main main){
 		this.main = main;
 	}
 	
 	@FXML
 	public TrainingSession getTrainingSessionInfo(TrainingSession session){
 		
 		try{
 		
 		String dateString = dato.getValue().toString();
 		String startTime = tid.getText().toString();
 		String duration = varighet.getText().toString();
 		String shape = personligForm.getText().toString();
 		session = new TrainingSession(dateString, startTime, duration, shape);
 		return session;
 		}
 		catch(Exception e){
 			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error");
			alert.setContentText("Invalid input time fields need to be integers!");
			alert.showAndWait();
 		}
 		return null;
 	}
 	@FXML
 	public void cancel(){
 		main.showVelkommen();
 	}
 	
 	@FXML
 	public void showAddExercises(){
 		System.out.println("test");
 		trainingSession = getTrainingSessionInfo(trainingSession);
 		if(trainingSession != null){
 			main.showAddExerciseToSession(trainingSession);
 		}
 		
 	}
 }