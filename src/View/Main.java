package View;

import Model.FileData;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("SelectProgram.fxml"));
        primaryStage.setTitle("SelectProgram");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


//        Stage secondStage = new Stage();
//
//        Parent root2 = FXMLLoader.load(getClass().getResource("RunProrgram.fxml"));
//        secondStage.setTitle("ToyLanguageInterpreter");
//        secondStage.setScene(new Scene(root2));
//        secondStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
