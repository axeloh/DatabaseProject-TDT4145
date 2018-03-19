package first_package;




import javafx.fxml.FXML;
import javafx.scene.control.Menu;


public class RootLayoutController {
	
	// Refererer til Main
	private Main main;
	
	/**
	 *  Blir kalt av main
	 * @param main Main-instansen som instansierer denne og som inneholder metodene for � bytte til de andre viewene.
	 */ 
	public void setMain(Main main){
		this.main = main;
	}
	
	//Lukker appen
	@FXML
    private void handleExit() {
        System.exit(0);
    }
	
}