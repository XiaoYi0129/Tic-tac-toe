package application;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class TicServer {

    private HashMap<Socket,TicService> socketTicServiceHashMap = new HashMap<>();
    private ArrayList<TicService> ticServiceList=new ArrayList<>();
    private HashMap<TicService,TicService> servicePair = new HashMap<>();
    public static void main(String[] args) throws IOException{
        final int SBAP_PORT = 7878;
        TicServer ticServer=new TicServer();
        ServerSocket server = new ServerSocket( SBAP_PORT);
        System.out.println( "Waiting for clients to connect..." );
        while (true) {
            Socket socket = server.accept();
            System.out.println( "Client connected." );
            TicService service = new TicService(socket);
            Thread thread = new Thread(service);
            thread.start();
            ticServer.getSocketTicServiceHashMap().put(socket,service);
            ticServer.getTicServiceList().add(service);
            if(ticServer.getTicServiceList().size()>1){
                System.out.println("Pair");
                TicService player1=ticServer.getTicServiceList().get(0);
                TicService player2=ticServer.getTicServiceList().get(1);

                player1.setOpponentService(player1);
                player2.setOpponentService(player2);

                player1.setThisClientNum(1);
                player2.setThisClientNum(2);

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
}
