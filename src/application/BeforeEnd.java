package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public  class BeforeEnd {

    BeforeEnd(Socket socket) {

        Thread t = new Thread(() -> {
            try {


                PrintWriter out=new PrintWriter(socket.getOutputStream());
                out.println("6Exit");
                out.flush();





                Thread.sleep(5 * 1000);
                System.out.println("end...");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        });
        Runtime.getRuntime().addShutdownHook(t);
    }
}
