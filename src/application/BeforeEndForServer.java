package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class BeforeEndForServer {
    BeforeEndForServer(ArrayList<Socket> socketList ) {


        Thread t = new Thread(() -> {
            try {

                socketList.forEach(socket ->{

                    try {
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                        out.println("7Exit");
                        out.flush();

                }catch (IOException e) {
                        e.printStackTrace();
                    }});





                Thread.sleep(5 * 1000);
                System.out.println("end...");



            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Runtime.getRuntime().addShutdownHook(t);
    }
}
