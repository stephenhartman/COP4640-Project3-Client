package project3_client;

import java.io.*;

/**
 * Client to send and receive data from Server project.
 */
public class Client {

    private static String HOST;
    private static int PORT;
    private static int NUM_CLIENTS;
    private static FileWriter outputCSV;
    private Client(int num_clients) {
        this.NUM_CLIENTS = num_clients;
    }

    /**
     * Main method, creates filewriter for CSV output and multi-threaded clients
     * @param args Command Line arguments
     * @throws IOException for FileWriter
     */
    public static void main(String[] args) throws IOException {
        HOST = args[0];
        PORT = Integer.parseInt(args[1]);
        NUM_CLIENTS = Integer.parseInt(args[2]);
        outputCSV = new FileWriter("output.csv", true);
        outputCSV.write("Number of Clients,Total turn-around time,Average turn-around time\n");
        new Client(NUM_CLIENTS).execute(HOST, PORT);
    }

    /**
     * Executes the Client object.  Creates client threads specified from command line
     * arguments and prints output to console and filewriter.
     * @param host hostname from args
     * @param port port from args
     */
    @SuppressWarnings("empty-statement")
    private void execute(String host, int port) {
        double overallTime = 0.000;
        Threaded_Client[] clients = new Threaded_Client[NUM_CLIENTS];   // Array of threaded clients

        try {
            System.out.println("Client Connected to Server " + HOST + " on Port: " + PORT);
            System.out.println("Transmitting " + NUM_CLIENTS + " Client Requests." );

            // Iteratively opens sockets on the threaded clients
            for (int x = 0; x < NUM_CLIENTS; x++) {
                (clients[x] = new Threaded_Client(host, port, x)).start();
                Thread.sleep(25);
            }
            // While number of clients finished < total client count
            while (clients[0].getNumFinished() < NUM_CLIENTS);

            // Iteratively prints threaded client data to console and increments total time
            for (Threaded_Client client : clients) {
                System.out.println(client.outputFromServer + " in " + client.totalTime + " ms.");
                overallTime += client.totalTime;
            }

            // Print summary output to CSV and console
            outputCSV.write(NUM_CLIENTS + ",");
            System.out.println("Total Turn-around Time: " + overallTime + " ms.");
            outputCSV.write(overallTime + ",");
            System.out.println("Average Turn-around Time: " + overallTime / NUM_CLIENTS + " ms.");
            outputCSV.write(overallTime / NUM_CLIENTS + "\n");
            outputCSV.close();
        }
        catch (InterruptedException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
