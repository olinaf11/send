package client;

import frame.Mafenetre;
import handler.ClientHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFileChooser;

/**
 * Client
 */
public class Client {
    Socket socket;
    DataOutputStream output;
    DataInputStream input;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataOutputStream getOutput() {
        return output;
    }

    public void setOutput(DataOutputStream output) {
        this.output = output;
    }

    public DataInputStream getInput() {
        return input;
    }

    public void setInput(DataInputStream input) {
        this.input = input;
    }
    public Client(){}
    public void connectToServer(String host, int port) throws IOException {
        setSocket(new Socket(host,port));
        ClientHandler clientHandler = new ClientHandler(getSocket(), new Mafenetre(800,600, getSocket()));
        Thread t = new Thread(clientHandler);
        t.start();
        System.out.println("Connectee");
    }
    public Client(String host, int port) throws IOException {
        setSocket(new Socket(host, port));
        setInput(new DataInputStream(getSocket().getInputStream()));
        setOutput(new DataOutputStream(getSocket().getOutputStream()));
    }
    public Client(Socket socket) throws IOException {
        setSocket(socket);
        setInput(new DataInputStream(getSocket().getInputStream()));
        setOutput(new DataOutputStream(getSocket().getOutputStream()));
    }
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.connectToServer("localhost",1011);
    }
    public void sendFile(File file, OutputStream out) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
//        send filename
        sendFileName(file.getName(), dos);
//        send data file;
        sendDataFile(file, dos);
        dos.flush();
    }
    public void sendFileName(String fileName, DataOutputStream dos) throws IOException {
        dos.writeInt(fileName.getBytes().length);
        dos.write(fileName.getBytes());
    }
    public void sendDataFile(File file, DataOutputStream dos) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] count = new byte[(int)file.length()];
        int t = fis.read(count);
        dos.writeInt(t);
        dos.write(count);
        fis.close();
    }
}