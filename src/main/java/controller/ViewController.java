package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ViewController {

    @FXML
    private TableColumn<?, ?> alphabetColumn1;

    @FXML
    private TableColumn<?, ?> alphabetColumn2;

    @FXML
    private TextField alphabetField;

    @FXML
    private TextField finalField;

    @FXML
    private TextField finalString;

    @FXML
    private Button generateGraphButton;

    @FXML
    private Button generateTableButton;

    @FXML
    private TextField initialField;

    @FXML
    private TextArea loggerArea;

    @FXML
    private TextField result;

    @FXML
    private TableColumn<?, ?> stateColumn;

    @FXML
    private TextField stateField;

    @FXML
    private TextField string;

}
