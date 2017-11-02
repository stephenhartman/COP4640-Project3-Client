package project3_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;
import java.net.Socket;

public class Threaded_Client extends Thread {

    private String host;
    private int port;
    private final static AtomicInteger numFinished = new AtomicInteger(0);
    protected String outputFromServer;
    protected long totalTime;
    private int clientNumber;

    protected Threaded_Client(String host, int port, int clientNumber) {
        this.host = host;
        this.port = port;
        this.clientNumber = clientNumber;
    }

    protected int getNumFinished() {
        return numFinished.get();
    }

    @Override
    public void run() {
        try(
            Socket socket = new Socket(host, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            long startTime = System.currentTimeMillis();        // Record start time
            out.println(clientNumber);                          // Output client number to Server
            outputFromServer = in.readLine();                   // Input String from Server
            totalTime = System.currentTimeMillis() - startTime; // Record total time
            numFinished.incrementAndGet();                      // Increment the clients processed integer
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(1);
        }
    }
}
