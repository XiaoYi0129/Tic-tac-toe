package application;

import application.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;

public class Main1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getClassLoader().getResource("mainUI.fxml"));
            Pane root = fxmlLoader.load();

            //Button button = new Button("Pair");


            primaryStage.setTitle("Tic Tac Toe1");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();


            TicClient ticClient=new TicClient("player1");
            ticClient.newClient();
            Controller controller=fxmlLoader.<Controller>getController();
            controller.setTicClient(ticClient);
            //controller.setPlayerNum(1);
            ticClient.setController(controller);













        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        /*TicClient ticClient=new TicClient("player1");
        ticClient.newClient();
        TicClient ticClient1 = new TicClient("player2");
        ticClient1.newClient();
*/
        launch(args);
    }
}
