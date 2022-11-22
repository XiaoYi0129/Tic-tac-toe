package application;

import application.controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TicClient implements Runnable
        //implements Runnable
       // extends Application
    {


    private String playerName;
   private Controller controller;
    private InputStream instream;
    private OutputStream outstream;
   private Scanner in;
    private PrintWriter out;
    private String command;
    private String response;
    private Socket socket;
    private  int playerNum=0;
    private  int opponentPlayerNum=0;
   private  boolean receiveResponse=false;
    private int playerListNum=0;


    public TicClient(String playerName){
        this.playerName=playerName;
    }


    public  void  newClient(
           // int playerNum
    ) throws IOException {
       // this.playerNum=playerNum;
        final int SERVER_PORT =4990;
        try
                //(Socket s = new Socket( "localhost", SBAP_PORT);
             //InputStream instream = s.getInputStream();
             //OutputStream outstream = s.getOutputStream()
        //)
        {
            Socket s = new Socket("localhost", SERVER_PORT);
            socket=s;
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());


            /*
            socket.setKeepAlive(true);
            socket.setSoTimeout(10);
            */
            /*in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());*/



            //sendCommandTOService("1ClientSendCommandToService");
            //receiveResponse();
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
            e.printStackTrace();
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
    public void sendCommand
    //TOService
            (String commandFromController){
        //this play want to change that player
        try {
            System.out.println(commandFromController);
            outstream = socket.getOutputStream();
            out=new PrintWriter(outstream);
            out.println(commandFromController);
            out.flush();

        }catch (IOException e){
            e.printStackTrace();
        }
    }




    public void run(){
       // try {
            //in = new Scanner(socket.getInputStream());
            //out = new PrintWriter(socket.getOutputStream());
            doService();
        //}catch (IOException e){
          //  e.printStackTrace();
       // }
    }
    public void doService(){


        while (true){
            /*if(controller==null){
                System.out.println("CLose");
            }*/
            if(!in.hasNext())return;
            String response = in.next();
            System.out.println(response);

            if(response.substring(0,1).equals("0")){
                //now pair
                //playerListNum=Integer.parseInt(response.substring(1,2));
                //System.out.println(playerListNum);
                int num=Integer.parseInt(response.substring(1,2));
                System.out.println(num);
                playerNum=num;

                opponentPlayerNum=Integer.parseInt(response.substring(2,3));

                controller.setPlayerNum(num);

                //if player1 , make sure here only read 2
                // if player2, make sure here only read
                }else if(Integer.parseInt(response.substring(0,1))==opponentPlayerNum){
                System.out.println("TicClient2 162");
                excecuteResponse12(response);
            }else if(response.substring(0,1).equals("5")){
                System.out.println("Opponent close penal");
            }else if(response.equals("6Exit")){
                System.out.println("Opponent exit");
            }else if(response.equals("7Exit")){
                System.out.println("Server exit");
            }

        }
    }
    public void excecuteResponse12(String response){
        System.out.println(response);
        //Not on FX application thread; currentThread = Thread-4
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                controller.opponentRefresh(Integer.parseInt(response.substring(1,2)),Integer.parseInt(response.substring(2,3)));
            }
        });

        //controller.opponentRefresh(Integer.parseInt(response.substring(1,2)),Integer.parseInt(response.substring(2,3)));
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
    public Socket getSocket(){
        return socket;
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setOpponentPlayerNum(int opponentPlayerNum) {
        this.opponentPlayerNum = opponentPlayerNum;
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


