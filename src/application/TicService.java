package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TicService implements Runnable{
    private Socket s;
    private Scanner in;
    private PrintWriter out;
    private int[][] chessBoard = new int[3][3];
    private String thisClientName=null;
    private int thisClientNum=0;
    private String opponentName;

    private TicService opponentService;

    public TicService(Socket aSocket){
        s=aSocket;
    }
    public void run() {
        try {
            //try {
                in = new Scanner( s.getInputStream());
                out = new PrintWriter( s.getOutputStream());
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
            executeCommand( command);
        }
    }
    public void executeCommand (String command) {
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
                System.out.println("player"+keyWord+" CAN put chess on "+x+","+y);
                break;
            case "2":
                response="Can put";
                chessBoard[x][y]=2;
                System.out.println("player"+keyWord+" CAN put chess on "+x+","+y);
                break;



        }

        out.println(response);
        out.flush();
    }

    public TicService getOpponentService() {
        return opponentService;
    }

    public void setOpponentService(TicService opponentService) {
        this.opponentService = opponentService;

    }

    public void setThisClientNum(int thisClientNum) {
        this.thisClientNum = thisClientNum;
    }
}
