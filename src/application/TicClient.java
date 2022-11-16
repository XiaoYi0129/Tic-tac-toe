package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TicClient {
    public static void main (String[] args) throws IOException {
        final int SBAP_PORT = 8888;
        try (Socket s = new Socket( "localhost", SBAP_PORT)) {
            InputStream instream = s.getInputStream();
            OutputStream outstream = s.getOutputStream();
            Scanner in = new Scanner( instream);
            PrintWriter out = new PrintWriter( outstream);
            String command;
            String response;
        }
    }
}
