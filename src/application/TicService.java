package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TicService implements Runnable{
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private int[][] chessBoard = new int[3][3];
    private String thisClientName=null;
    private int thisClientNum=0;
    private TicService opponentTicService;

    private InputStream instream;
    private OutputStream outstream;
    private String response;


    private TicService opponentService;

    public TicService(Socket aSocket){
        socket=aSocket;
    }
    public void run() {
        try {
            //try {
                in = new Scanner( socket.getInputStream());
                out = new PrintWriter( socket.getOutputStream());
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
            if (!in.hasNext()) return;
            String command = in.next();
            if(command.substring(0,1).equals("1")){
            executeCommand( command);}
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

        sendCommandtoClient("2ServiceSendCommandToClient");
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
        }catch (IOException e){
            e.printStackTrace();
        }
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
}
