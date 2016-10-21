package org.betavzw.jadvanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Jef on 13/09/2016.
 */
public class Main {
    private static final int PORT_NUMBER=4242;
    public static void main(String[] args) throws IOException {
        AtomicInteger atomicTeller = new AtomicInteger();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Boolean notStarted = true;
        try(ServerSocket socket = new ServerSocket(PORT_NUMBER)){
            System.out.println("Accepting clients");
            do {
                Socket clientSocket;
                try {
                    clientSocket= socket.accept();
                    socket.setSoTimeout(1000);
                    System.out.println("Client accepted");
                    atomicTeller.incrementAndGet();
                    executor.execute(new ClientThread(clientSocket, () -> atomicTeller.decrementAndGet()));
                }catch(SocketTimeoutException ex) {
                }
            }while(atomicTeller.intValue() > 0 );
            System.out.println("Server has ended");
        }
        System.out.println("Socket closed");
        executor.shutdown();
    }
}
@FunctionalInterface
interface Command{
    void execute();
}
class ClientThread implements Runnable{
    private Command closingCommand;
    private Socket clientSocket;
    public ClientThread(Socket client, Command closingCommand){
        this.clientSocket = client;
        this.closingCommand = closingCommand;
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
            closingCommand.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Client has ended");
    }
}

