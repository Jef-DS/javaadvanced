package org.betavzw.javaadvanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Jef on 7/09/2016.
 */
public class Main {
    private static final int PORT_NUMBER=4242;
    public static void main(String[] args) throws IOException {

        Executor executor = Executors.newFixedThreadPool(2);
        try(ServerSocket socket = new ServerSocket(PORT_NUMBER)){
            while(true) {
                Socket clientSocket = socket.accept();
                executor.execute(new ClientThread(clientSocket));
            }
        }
    }
}

class ClientThread implements Runnable{
    private Socket clientSocket;
    public ClientThread(Socket client){
        this.clientSocket = client;
    }
    @Override
    public void run() {
        try(PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){
            String inputLine, outputLine;
            while ((inputLine = in.readLine())!= null){
                System.out.println(inputLine);
                if (inputLine.equals(".exit")) break;
                outputLine = "Echo: " + inputLine;
                out.println(outputLine);
            }
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
