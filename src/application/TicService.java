package application;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class TicService implements Runnable{
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private int[][] chessBoard = new int[3][3];
    private String thisClientName=null;
    //private int thisClientNum=0;
    private TicService opponentTicService;

    private InputStream instream;
    private OutputStream outstream;
    private String response;
    private int playerNum=0;
    private int playerListNum=0;

    //Exception：https://blog.csdn.net/whatday/article/details/100121412


   // private TicService opponentService;
    private Socket opponentSocket;

    public TicService(Socket aSocket,int playerListNum){
        socket=aSocket;
        this.playerListNum=playerListNum;
    }

    public void run() {
        try {
            //try {

                in = new Scanner( socket.getInputStream());
                out = new PrintWriter( socket.getOutputStream());
                /*Runnable test = ()-> {
                    while (true){
                        try {
                            socket.setSoTimeout(5000);
                            String command = "Test";
                            out.println(command);
                            out.flush();
                            Thread.sleep(1000);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                Thread testThread = new Thread(test);
                testThread.start();*/

                doService();


            //}
            //finally {
                //System.out.println("close");
               //s.close();
           // }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public void doService() throws IOException {
        while (true) {
            /*if(playerListNum!=0 &&isServerClose(opponentSocket)){
                System.out.println("ExceptionOpponentLeave");
                return;
            }*/
            /*if(playerNum!=0) {
                try {
                    opponentSocket.sendUrgentData(0xFF);
                } catch (Exception ex) {
                    System.out.println("2close");
                }
            }*/

            if (!in.hasNext()) return;
            String command = in.next();
            if(command.substring(0,1).equals("0")){

            } else if(Integer.parseInt(command.substring(0,1))==playerNum){
                //this player want to change opponent
            executeCommand( command);
            }else if(command.substring(0,1).equals("5")){
                //41 is this close penal
                sendCommandToOpponentSocket(command);
                System.out.println("player "+command.substring(1,2)+"close penal");
            }else if(command.equals("6Exit")){
                System.out.println("player "+playerNum+"exit ");
                executeCommand("Exit");
            }
        }
    }
    public void executeCommand (String command) {


        /*
        if(command.substring(0,1).equals("O")){
            return;
        }
        System.out.println("test");
        if(command.substring(0,1).equals("0")) {
            System.out.println("test");//no
            String[] commandArray = command.split(",");
            thisClientName = commandArray[1];
            if (thisClientNum == 0) {
                String response = "No opponent, wait";
                out.println(response);
                out.flush();
                return;
            }else{
                String response = thisClientNum+"Have opponent";
                System.out.println(response);
                out.println(response);
                out.flush();
                return;
            }
        }
        //Can Reach System.out.println("Service can receive command");
        String keyWord = command.substring(0,1);
        int  x=Integer.parseInt(command.substring(2,3));
        int  y=Integer.parseInt(command.substring(4,5));
        System.out.println("player"+keyWord+" want to put chess on "+x+","+y);
        String response="response fail";
        switch (keyWord){



            case "1":
                response="Can put";
                chessBoard[x][y]=1;
                //opponentTicService.sendOpponentCommand("O"+x+","+y);
                System.out.println("player"+keyWord+" CAN put chess on "+x+","+y);
                break;
            case "2":
                response="Can put";
                chessBoard[x][y]=2;
                System.out.println("player"+keyWord+" CAN put chess on "+x+","+y);
                break;



        }
        */
        //response="Service receive command";
        System.out.println(command);

        //opponentTicService.sendCommandtoClient(command);
        if(command.substring(0,1)=="0"){
        sendCommandtoClient(command);
        return;}else {
            sendCommandToOpponentSocket(command);
        }
        /*
        out.println(response);
        out.flush();*/
    }

    public void sendCommandtoClient(String command){
        try {
            outstream = socket.getOutputStream();
            out=new PrintWriter(outstream);
            out.println(command);
            out.flush();

            /*if(playerNum!=0) {
                try {
                    opponentSocket.sendUrgentData(0xFF);
                } catch (Exception ex) {
                    System.out.println("2close");
                }
            }*/

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void sendCommandToOpponentSocket(String command){
        try {
            outstream = opponentSocket.getOutputStream();
            out=new PrintWriter(outstream);
            out.println(command);
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }
    /*
    public TicService getOpponentService() {
        return opponentService;
    }

    public void setOpponentService(TicService opponentService) {
        this.opponentService = opponentService;

    }

    public void setThisClientNum(int thisClientNum) {
        this.thisClientNum = thisClientNum;
    }

    public void sendOpponentCommand(String command){
        try {
            if(command.substring(0,2).equals("OP")){
                instream = socket.getInputStream();
                outstream = socket.getOutputStream();
                in = new Scanner(instream);
                out = new PrintWriter(outstream);
                System.out.println("1 and 2 pair");
                out.println(command);
                out.flush();
                response = in.nextLine();
                System.out.println("receive" + response);
            }

            instream = socket.getInputStream();
            outstream = socket.getOutputStream();
            in = new Scanner(instream);
            out = new PrintWriter(outstream);
            //command = "PLAY1" + commandFromController;
            //command : x,y
            command = "O" + command;
            System.out.println("request " + "opponent want to change this controller" +command);
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

    public void setOpponentSocket(Socket s) {
        opponentSocket=s;
        System.out.println("Set opponent socket success");
    }
    //https://blog.csdn.net/qq_43842093/article/details/120582398
    public Boolean isServerClose(Socket socket){
        try{
            socket.sendUrgentData(0xFF);
            return false;
        }catch(Exception se){
            return true;
        }
    }
}
