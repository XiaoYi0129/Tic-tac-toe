package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TicServer {

    private HashMap<Socket,TicService> socketTicServiceHashMap = new HashMap<>();
    private ArrayList<TicService> ticServiceList=new ArrayList<>();
    private HashMap<TicService,TicService> servicePair = new HashMap<>();

    //to client
    //private ArrayList<Socket> socketFromClientList = new ArrayList<>();
    private ArrayList<TicServer> ticServerList = new ArrayList<>();
    private InputStream instream;
    private OutputStream outstream;
    private Scanner in;
    private PrintWriter out;
    private String command;
    private String response;
    private Socket socketServerToClient;

    private boolean canConnectWithClientService = false;
    //


    public static void main(String[] args) throws IOException{
        final int SERVER_PORT =2456;
        TicServer ticServer=new TicServer();
        ServerSocket server = new ServerSocket( SERVER_PORT);
        System.out.println( "Waiting for clients to connect..." );
        while (true) {
            Socket socket = server.accept();
            System.out.println( "Client connected." );
            TicService service = new TicService(socket);
            Thread thread = new Thread(service);
            thread.start();
            ticServer.getSocketTicServiceHashMap().put(socket,service);
            ticServer.getTicServiceList().add(service);

            //receive client socket
            /*
            final int CLIENT_PORT = 344;
            Socket sForClient = new Socket("localhost1",CLIENT_PORT);
            //ticServer.getSocketFromClientList().add(sForClient);
            ticServer.setSocketToClient(sForClient);*/


            //

            if(ticServer.getTicServiceList().size()>1){
                System.out.println("Pair");
                TicService player1=ticServer.getTicServiceList().get(0);
                TicService player2=ticServer.getTicServiceList().get(1);

                /*
                player1.setOpponentService(player1);
                player2.setOpponentService(player2);

                player1.setThisClientNum(1);

                player2.setThisClientNum(2);

                player1.sendOpponentCommand("OP"+1);
                player2.sendOpponentCommand("OP"+2);
                */


                ticServer.getServicePair().put(player1,
                        player2);




            }
        }

    }
    public HashMap<Socket,TicService> getSocketTicServiceHashMap(){
        return socketTicServiceHashMap;
    }
    public ArrayList<TicService> getTicServiceList(){
        return ticServiceList;
    }

    public HashMap<TicService, TicService> getServicePair() {
        return servicePair;
    }

   /*public ArrayList<Socket> getSocketFromClientList(){
        return socketFromClientList;
    }*/
    public void setSocketToClient(Socket s){
        socketServerToClient=s;
    }

    public void sendCommand(String command){

    }
}
