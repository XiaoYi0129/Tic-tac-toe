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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TicClient
        //implements Runnable
       // extends Application
{


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
    boolean receiveResponse=false;


    public TicClient(String playerName){
        this.playerName=playerName;
    }


    public  void  newClient(
           // int playerNum
    ) throws IOException {
       // this.playerNum=playerNum;
        final int SERVER_PORT =2456;
        try
                //(Socket s = new Socket( "localhost", SBAP_PORT);
             //InputStream instream = s.getInputStream();
             //OutputStream outstream = s.getOutputStream()
        //)
        {
            Socket s = new Socket( "localhost",SERVER_PORT);
            socket=s;
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
            sendCommandTOService("1ClientSendCommandToService");
            receiveResponse();
            //run();

            //sendPairCommand();
            //this.instream=instream;
            //this.outstream=outstream;
            //instream = socket.getInputStream();//Socket is closed
            //outstream = socket.getOutputStream();
            //in = new Scanner(instream);
            //out = new PrintWriter(outstream);


            //create client socket
            /*final int CLIENT_PORT = 344;
            ServerSocket serverInClient = new ServerSocket(CLIENT_PORT);
            System.out.println("Waiting for server to connect...");
            Socket sForServe =serverInClient.accept();
            System.out.println( "Server connected." );
            ClientService clientService = new ClientService(sForServe);
            Thread thread = new Thread(clientService);
            thread.start();*/
            //


        }catch (Exception e){
            System.out.println("Exception");
        }
    }
    /*
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
    */
    public void sendCommandTOService(String commandFromController){
        try {
            outstream = socket.getOutputStream();
            out=new PrintWriter(outstream);
            out.println(commandFromController);
            out.flush();

        }catch (IOException e){
            e.printStackTrace();
        }
    }




    public void receiveResponse(){

        while (true){
            if(!in.hasNext())return;
            String response = in.next();
            if(response.substring(0,1).equals("2")){
            excecuteResponse(response);}
        }
    }
    public void excecuteResponse(String response){

        System.out.println(response);
    }
    /*
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
    */



    public void setSocket(Socket s){
        socket=s;
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    //////////

    /*
    public void run(){
        try {
            System.out.println("test1");
            in = new Scanner( socket.getInputStream());
            out = new PrintWriter( socket.getOutputStream());
            doService();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void doService() throws IOException {
        while (true) {
            System.out.println("test2");
            if (!in.hasNext()) return;
            String command = in.next();
            executeCommand( command);
        }
    }

    public void executeCommand(String command){

        //only about opponent command

        System.out.println("test3");
        if(!command.substring(0,1).equals("O")){
            return;
        }
        if (command.substring(0,2).equals("OP")){
            playerNum=Integer.parseInt(command.substring(2,3));
            controller.setPlayerNum(Integer.parseInt(command.substring(2,3)));
            response="pair success";
            System.out.println(response);
            out.println(response);
            out.flush();
            return;
        }
        int x=Integer.parseInt(command.substring(1,2));
        int y=Integer.parseInt(command.substring(3,4));
        if(controller.opponentRefresh(x,y)){
            System.out.println("opponent refresh this player");
            response="O"+"OK";
        }

        out.println(response);
        out.flush();

    }
*/


    /*
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
    */






}


