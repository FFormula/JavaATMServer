package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Socketor {

    private int port;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Socketor(int port) {
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException("Server-socket klaida: " + e.getMessage());
        }
    }

    public void accept() {
        try {
            clientSocket = serverSocket.accept();
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException("Kliento prisijungimo klaida: " + e.getMessage());
        }
    }

    public List<String> getRequest() {
        ArrayList<String> request = new ArrayList<>();
        try {
            int lines = Integer.parseInt(reader.readLine());
            for (int j = 0; j < lines; j ++)
                 request.add(reader.readLine());
            return request;
        } catch (IOException e) {
            return request;
        }
    }

    public void sendResponse(String response) {
        try {
            writer.write(response);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException("Klaida siunciant atsakyma klientui: " + e.getMessage());
        }
    }

    public void closeClient() {
        try {
            reader.close();
            writer.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Klaida uzdarant prisijungima: " + e.getMessage());
        }
    }
}
