package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class mainController {

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
    private CheckBox checkBox;

    @FXML
    private Button btnAdd;

    @FXML
    void btnLeggTil(ActionEvent event) {
    	System.out.println("TEST");
    }

}
