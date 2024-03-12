package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //System.out.println(getClass().getResource("../resources/xml/View.fxml"));

        FXMLLoader  loader = new FXMLLoader(getClass().getResource("../resources/xml/View.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("AFD SIMULATOR");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
