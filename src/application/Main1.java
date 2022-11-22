package application;

import application.controller.Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.io.IOException;



public class Main1 extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("mainUI.fxml"));

           // fxmlLoader.setLocation(getClass().getClassLoader().getResource("mainUI.fxml"));
            Pane root = fxmlLoader.load();

            //Button button = new Button("Pair");

            primaryStage.setTitle("Tic Tac Toe1");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
            //http://www.qb5200.com/article/307966.html


            TicClient ticClient=new TicClient("player1");
            ticClient.newClient();
            Thread thread= new Thread(ticClient);
            thread.start();

            Controller controller= fxmlLoader.getController();
            controller.setTicClient(ticClient);

            ticClient.setController(controller);
            //ticClient.newClient();
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

                @Override

                public void handle(WindowEvent event) {

                    System.out.print("listen player1 close penal");
                    ticClient.sendCommand("51");




                }

            });
            new BeforeEnd(ticClient.getSocket());

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
