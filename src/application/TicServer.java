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

    private HashMap<TicService,TicClient> ServiceClientHashMap = new HashMap<>();
    private ArrayList<TicService> ticServiceList=new ArrayList<>();
    private HashMap<TicService,TicService> servicePair = new HashMap<>();

    //to client
    private ArrayList<Socket> socketFromClientList = new ArrayList<>();
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
        final int SERVER_PORT = 4990;
        //TicServer ticServer=new TicServer();
        //HashMap<TicService,TicClient> ServiceClientHashMap = new HashMap<>();
        ArrayList<TicService> ticServiceList=new ArrayList<>();
        //HashMap<TicService,TicService> servicePair = new HashMap<>();
        ArrayList<Socket> socketList = new ArrayList<>();


        new BeforeEndForServer(socketList);


        ServerSocket server = new ServerSocket( SERVER_PORT);
        System.out.println( "Waiting for clients to connect..." );
        int i=1;
        while (true) {
            Socket socket = server.accept();
            socketList.add(socket);

            System.out.println( "Client connected." );
            TicService service = new TicService(socket,i);
            i++;
            Thread thread = new Thread(service);
            thread.start();
            ticServiceList.add(service);
            //ticServer.getSocketTicServiceHashMap().put(socket,service);


            //socket.setSoTimeout(5000);

            //receive client socket
            /*
            final int CLIENT_PORT = 344;
            Socket sForClient = new Socket("localhost1",CLIENT_PORT);
            //ticServer.getSocketFromClientList().add(sForClient);
            ticServer.setSocketToClient(sForClient);*/


            //
            if(socketList.size()==1){
                TicService service1=ticServiceList.get(0);
                service1.sendCommandtoClient("4NoOpponentPleaseWait");
            }
            if(socketList.size()>1){
                System.out.println("Pair");
                TicService service1=ticServiceList.get(0);
                TicService service2=ticServiceList.get(1);

                Socket socket1=socketList.get(0);
                Socket socket2=socketList.get(1);

                /*
                player1.setOpponentService(player1);
                player2.setOpponentService(player2);

                player1.setThisClientNum(1);

                player2.setThisClientNum(2);

                player1.sendOpponentCommand("OP"+1);
                player2.sendOpponentCommand("OP"+2);
                */
                service1.setPlayerNum(1);
                service1.setOpponentSocket(socket2);



                service2.setPlayerNum(2);
                service2.setOpponentSocket(socket1);

                service1.sendCommandtoClient("0"+"1"+"2"+"play2");

                service2.sendCommandtoClient("0"+"2"+"1"+"play1");

                /*if(socket1.isClosed()){
                  System.out.println("close1");

                    //service2.sendCommandtoClient("ExceptionOpponentLeave");
                }*/
                if(socket2.isClosed()){
                    service1.sendCommandtoClient("ExceptionOpponentLeave");
                }

                //ticServer.getServicePair().put(service1,service2);




            }
            if(socketList.size()==3){
                TicService service1=ticServiceList.get(2);
                service1.sendCommandtoClient("4NoOpponentPleaseWait");
            }
            if(socketList.size()==4){
                System.out.println("Pair");
                TicService service3=ticServiceList.get(2);
                TicService service4=ticServiceList.get(3);

                Socket socket3=socketList.get(2);
                Socket socket4=socketList.get(3);

                /*
                player1.setOpponentService(player1);
                player2.setOpponentService(player2);

                player1.setThisClientNum(1);

                player2.setThisClientNum(2);

                player1.sendOpponentCommand("OP"+1);
                player2.sendOpponentCommand("OP"+2);
                */
                service3.setPlayerNum(1);
                service3.setOpponentSocket(socket4);



                service4.setPlayerNum(2);
                service4.setOpponentSocket(socket3);

                service3.sendCommandtoClient("0"+"1"+"2"+"play4");

                service4.sendCommandtoClient("0"+"2"+"1"+"play3");

            }
        }

    }
    public HashMap<TicService,TicClient> getServiceClientHashMap(){
        return ServiceClientHashMap;
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