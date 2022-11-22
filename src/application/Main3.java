package application;

import application.controller.Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main3 extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("mainUI.fxml"));

            // fxmlLoader.setLocation(getClass().getClassLoader().getResource("mainUI.fxml"));
            Pane root = fxmlLoader.load();

            //Button button = new Button("Pair");

            primaryStage.setTitle("Tic Tac Toe3");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();


            TicClient ticClient=new TicClient("player3");
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

                    System.out.print("listen player3 close penal");
                    ticClient.sendCommand("53");

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
