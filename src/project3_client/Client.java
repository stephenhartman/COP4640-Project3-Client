package project3_client;

import java.io.*;

public class Client {

    private static String HOST;
    private static int PORT;
    private static int NUM_CLIENTS;
    private static FileWriter outputCSV;
    private Client(int num_clients) {
        this.NUM_CLIENTS = num_clients;
    }

    public static void main(String[] args) throws IOException {
        HOST = args[0];
        PORT = Integer.parseInt(args[1]);
        NUM_CLIENTS = Integer.parseInt(args[2]);
        outputCSV = new FileWriter("output.csv", true);
        outputCSV.write("Number of Clients,Total turn-around time,Average turn-around time\n");
        new Client(NUM_CLIENTS).execute(HOST, PORT);
    }

    @SuppressWarnings("empty-statement")
    private void execute(String host, int port) {
        double overallTime = 0.000;
        Threaded_Client[] clients = new Threaded_Client[NUM_CLIENTS];

        try {
            System.out.println("Client Connected to Server " + HOST + " on Port: " + PORT);
            System.out.println("Transmitting " + NUM_CLIENTS + " Client Requests..." );

            for (int x = 0; x < NUM_CLIENTS; x++) {
                (clients[x] = new Threaded_Client(host, port, x)).start();
                Thread.sleep(10);
            }
            while (clients[0].getNumFinished() < NUM_CLIENTS);


            for (Threaded_Client client : clients) {
                System.out.println(client.outputFromServer + " in " + client.totalTime + " ms.");
                overallTime += client.totalTime;
            }

            outputCSV.write(NUM_CLIENTS + ",");
            System.out.println("Total Turn-around Time: " + overallTime + " ms.");
            outputCSV.write(overallTime + ",");
            System.out.println("Average Turn-around Time: " + overallTime / NUM_CLIENTS + " ms.");
            outputCSV.write(overallTime / NUM_CLIENTS + "\n");
        }
        catch (InterruptedException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
