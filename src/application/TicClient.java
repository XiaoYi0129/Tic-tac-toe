package application;

import application.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TicClient extends Application {


    String playerName;
    Controller controller;
    InputStream instream;
    OutputStream outstream;
    Scanner in;
    PrintWriter out;
    String command;
    String response;
    Socket socket;
    int playerNum=0;


    public TicClient(String playerName){
        this.playerName=playerName;
    }


    public  void  newClient(
           // int playerNum
    ) throws IOException {
       // this.playerNum=playerNum;
        final int SBAP_PORT =7878;
        try
                //(Socket s = new Socket( "localhost", SBAP_PORT);
             //InputStream instream = s.getInputStream();
             //OutputStream outstream = s.getOutputStream()
        //)
        {
            Socket s = new Socket( "localhost", SBAP_PORT);
            socket=s;
            //sendPairCommand();
            //this.instream=instream;
            //this.outstream=outstream;
            //instream = socket.getInputStream();//Socket is closed
            //outstream = socket.getOutputStream();
            //in = new Scanner(instream);
            //out = new PrintWriter(outstream);

        }catch (Exception e){
            System.out.println("Exception");
        }
    }

    public void sendCommand(String commandFromController) {
        try {
            instream = socket.getInputStream();
            outstream = socket.getOutputStream();
            in = new Scanner(instream);
            out = new PrintWriter(outstream);
            //command = "PLAY1" + commandFromController;
            command = playerNum +"," +commandFromController;
            System.out.println("request " + command);
            out.println(command);
            out.flush();
            response = in.nextLine();
            System.out.println("receive" + response);
       }
        catch (IOException e){
            e.printStackTrace();
        }


    }
    public boolean sendPairCommand(){
        try {
            instream = socket.getInputStream();
            outstream = socket.getOutputStream();
            in = new Scanner(instream);
            out = new PrintWriter(outstream);
            command = 0 + ","+playerName+"," + " want to have an opponent";
            System.out.println(command);
            out.flush();
            response=in.nextLine();
            System.out.println("receive"+response);
            if(response.substring(0,1).equals("N")){
                return false;
            }else{
            playerNum=Integer.parseInt(response.substring(0,1));
            return true;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
    public void setSocket(Socket s){
        socket=s;
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public int getPlayerNum() {
        return playerNum;
    }


    public static void main (String[] args) throws IOException {
        final int SBAP_PORT = 33;
        try (Socket socket = new Socket( "localhost", SBAP_PORT)) {
            InputStream instream = socket.getInputStream();
            OutputStream outstream = socket.getOutputStream();
            Scanner in = new Scanner( instream);
            PrintWriter out = new PrintWriter( outstream);
            String command;
            String response;



            launch(args);
        }
    }




    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getClassLoader().getResource("mainUI.fxml"));
            Pane root = fxmlLoader.load();

            Controller controller=fxmlLoader.<Controller>getController();
           // controller.test();
            primaryStage.setTitle("Tic Tac Toe");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }





}


