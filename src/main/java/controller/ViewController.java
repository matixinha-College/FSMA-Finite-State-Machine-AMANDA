package main.java.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import main.java.automaton.Automaton;

import main.java.automaton.Automaton.AutomatonTransition;

public class ViewController implements Initializable {

    @FXML
    private Button EvaluateButton;

    @FXML
    private TableColumn<AutomatonTransition, String> alphabetColumn1;

    @FXML
    private TableColumn<AutomatonTransition, String> alphabetColumn2;

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
    private Button saveTableButton;

    @FXML
    private TableColumn<AutomatonTransition, String> stateColumn;

    @FXML
    private TextField stateField;

    @FXML
    private TextField string;

    @FXML
    private TableView<AutomatonTransition> tableView;

    Automaton automaton = new Automaton();

    @FXML
    void GenerateTable(ActionEvent event) {

        String[] alphabet = alphabetField.getText().split(",");
        for (String letter : alphabet) {
            automaton.getAlphabet().add(letter);
        }

        String[] states = stateField.getText().split(",");
        for (String state : states) {
            automaton.getStates().add(state);
        }

        automaton.setInitialState(initialField.getText());

        String[] finalStates = finalField.getText().split(",");
        for (String finalState : finalStates) {
            automaton.getFinalStates().add(finalState);
        }

        alphabetColumn1.setText(alphabet[0]);
        alphabetColumn2.setText(alphabet[1]);

        for (String state : states) {
            Automaton.AutomatonTransition transition = automaton.new AutomatonTransition();
            transition.setCurrentState(state);
            transition.setSymbolState(new HashMap<String, String>());
            transition.getSymbolState().put(alphabet[0], "");
            transition.getSymbolState().put(alphabet[1], "");
            automaton.getTransitions().add(transition);
        }

        stateColumn.setCellValueFactory(new PropertyValueFactory<>("currentState"));

        alphabetColumn1.setCellValueFactory(cellData -> {
            AutomatonTransition transition = cellData.getValue();
            return new SimpleStringProperty(transition.getSymbolState().get(alphabetColumn1.getText()));
        });

        alphabetColumn2.setCellValueFactory(cellData -> {
            AutomatonTransition transition = cellData.getValue();
            return new SimpleStringProperty(transition.getSymbolState().get(alphabetColumn2.getText()));
        });

        ObservableList<AutomatonTransition> list = FXCollections.observableArrayList(automaton.getTransitions());

        // make observable list
        tableView.setItems(list);

        // show automaton
        automaton.showAutomaton();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView.setEditable(true); // Define a tabela como editável

        // Configura as colunas para serem editáveis
        stateColumn.setEditable(false);
        alphabetColumn1.setEditable(true);
        alphabetColumn2.setEditable(true);

        // Configura os métodos de edição para cada coluna, se necessário
        stateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        alphabetColumn1.setCellFactory(TextFieldTableCell.forTableColumn());
        alphabetColumn2.setCellFactory(TextFieldTableCell.forTableColumn());

        // Define os eventos de edição para cada coluna
        stateColumn.setOnEditCommit(event -> {
            TablePosition<AutomatonTransition, ?> cell = event.getTableView().getEditingCell();
            AutomatonTransition transition = event.getTableView().getItems().get(cell.getRow());
            transition.setCurrentState(event.getNewValue());
        });
        alphabetColumn1.setOnEditCommit(event -> {
            TablePosition<AutomatonTransition, ?> cell = event.getTableView().getEditingCell();
            AutomatonTransition transition = event.getTableView().getItems().get(cell.getRow());
            transition.getSymbolState().put(alphabetColumn1.getText(), event.getNewValue());
        });
        alphabetColumn2.setOnEditCommit(event -> {
            TablePosition<AutomatonTransition, ?> cell = event.getTableView().getEditingCell();
            AutomatonTransition transition = event.getTableView().getItems().get(cell.getRow());
            transition.getSymbolState().put(alphabetColumn2.getText(), event.getNewValue());
        });
    }

    @FXML
    void SaveTable(ActionEvent event) {
        tableView.setEditable(true);

        // print the values of the table
        for (int i = 0; i < tableView.getItems().size(); i++) {
            System.out.println(stateColumn.getCellData(i) + " " + alphabetColumn1.getCellData(i) + " "
                    + alphabetColumn2.getCellData(i));
        }

        // edit transition based on the table
        automaton.getTransitions().clear();

        String[] SymbolStates = {alphabetColumn1.getText(),
        alphabetColumn2.getText()};
        for (int i = 0; i < tableView.getItems().size(); i++) {
        Automaton.AutomatonTransition transition = automaton.new
        AutomatonTransition();
        transition.setCurrentState(stateColumn.getCellData(i));
        transition.setSymbolState(new HashMap<String, String>());
        transition.getSymbolState().put(SymbolStates[0],
        alphabetColumn1.getCellData(i));
        transition.getSymbolState().put(SymbolStates[1],
        alphabetColumn2.getCellData(i));
        automaton.getTransitions().add(transition);
        }

         automaton.showAutomaton();
    }

    @FXML
    void Evaluate(ActionEvent event) {
        String input = string.getText();
        String currentState = initialField.getText();
        loggerArea.clear();

        for (int i = 0; i < input.length(); i++) {
            String symbol = String.valueOf(input.charAt(i));
            String nextState = automaton.getNextState(currentState, symbol);
            loggerArea.appendText("&(" + currentState + " , " + symbol + ") -> " + nextState + "\n");
            

            if (nextState == null) {
                result.setText("Invalid input");
                return;
            }

            currentState = nextState;
        }

        if (automaton.isFinalState(currentState)) {
            result.setText("Accepted");
            finalString.setText(currentState);
        } else {
            result.setText("Rejected");
            finalString.setText("");
        }       

        automaton.TransitionDOT();
        writeDotFile(automaton.TransitionDOT());
    }
        
    private void writeDotFile(String[] transitionDOT) {
        String dotPath = "src/main/resources/dot/automaton.dot";

        String dot = "digraph finite_state_machine {\n";
        dot += "rankdir=LR;\n";
        dot += "size=\"8,5\"\n";
        dot += "node [shape = doublecircle]; ";
        for (String finalState : automaton.getFinalStates()) {
            dot += finalState + " ";
        }
        dot += ";\n";
        dot += "node [shape = circle];\n";
        for (String transition : transitionDOT) {
            dot += transition;
        }
        dot += "}";

        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(dotPath);
            fileWriter.write(dot);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    void GenerateGraph(ActionEvent event) {
        try {
            String dotPath = "src/main/resources/dot/automaton.dot";
            String imgPath = "src/main/resources/img/automaton.png";
            String dotCommand = "dot -Tpng " + dotPath + " -o " + imgPath;
            Runtime.getRuntime().exec(dotCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
