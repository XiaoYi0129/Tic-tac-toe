package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientService implements  Runnable{
    private Socket s;
    private Scanner in;
    private PrintWriter out;

    public ClientService(Socket s){
        this.s=s;
    }

    public void run(){
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
    public void executeCommand (String command){

    }
}
