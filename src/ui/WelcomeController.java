package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WelcomeController {
	
	@FXML
	private Button btnNyOv;
	@FXML
	private Button btnRegTren;
	@FXML
	private Button btnTidligereTren;
	
	@FXML
	private Button btnSlett;
	
	// Refererer til Main
	private Main main;
	/**
	 * @param main Main-instansen som instansierer denne og som inneholder metodene for � bytte til de andre viewene.
	 */
	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML
	public void handleNyOvelse() {
		main.showNyOvelse();
	}
	
	@FXML
	public void handleRegistrerTreningsokt() {
		main.showAddTrainingSession();
	}
	
	@FXML
	public void handleTidligereTreningsokter() {
		main.showVisTrening();
		
	}
	
	@FXML
	public void handleSlett() {
		System.out.println("Yo");
		main.showDeleteExercise();
	}
}
