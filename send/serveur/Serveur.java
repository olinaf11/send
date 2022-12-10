package serveur;

import client.Client;
import handler.ServerHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Serveur {
    DataInputStream input;
    DataOutputStream output;
    ServerSocket serverSocket;
    Socket[] socketSec;
    Client client;
    String option;

    public DataInputStream getInput() {
        return input;
    }

    public void setInput(DataInputStream input) {
        this.input = input;
    }

    public DataOutputStream getOutput() {
        return output;
    }

    public void setOutput(DataOutputStream output) {
        this.output = output;
    }

    public void setSocketSec(Socket[] socketSec) {
        this.socketSec = socketSec;
    }

    public Socket[] getSocketSec() {
        return socketSec;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Serveur() throws IOException {
        setClient(new Client());
        setSocketSec(new Socket[3]);
        for (int i = 0; i < getSocketSec().length; i++) {
            getSocketSec()[i] = new Socket("localhost", 1011+i+1);
        }
    }
    public void openServer(int port) throws IOException {
        System.out.println("Wait for the client");
        setServerSocket(new ServerSocket(port));
        System.out.println("Server open");
        boolean first = true;
        while (true){
            Socket client = getServerSocket().accept();
            setClient(new Client(client));
            ServerHandler handler = new ServerHandler(this, first);
            Thread t = new Thread(handler);
            t.start();
        }
    }
    public String getFileName() throws IOException {
        int length = getClient().getInput().readInt();
        byte[] b = new byte[length];
        getClient().getInput().readFully(b, 0, length);
        return new String(b);
    }
    public void getfirstoption() {
        try {
            ObjectInputStream obj1=new ObjectInputStream(getClient().getSocket().getInputStream());
            setOption((String)obj1.readObject());
            System.out.println("Option:"+getOption());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return;
    }
    public void sendFile() throws Exception{
        String name = getFileName();


        int count = getClient().getInput().readInt();

        System.out.println("taille = "+count);

        byte[] fileContentBytes = new byte[count];

        int divide = count / 3;
        int off = 0;

        DataOutputStream out = null;
        Socket socket = null;
        for(int increment = 1; increment <= 3 ; increment++, off = divide, divide+=divide){
            //manoratra anle contenue
            socket = new Socket("localhost", 1011+increment);
            out = new DataOutputStream(socket.getOutputStream());
            out.writeInt(name.getBytes().length);
            out.write(name.getBytes());
            if (increment == 3 ) divide=count-off;
            System.out.println("de "+off+" a "+divide);
            out.write(fileContentBytes, off, divide);
            out.close();
            socket.close();
        }
    }
    public static void setFile(DataInputStream input, String folder) throws IOException {
        String fileName = getName(input);
        FileOutputStream out = new FileOutputStream(new File(folder + fileName));
        byte[] b = new byte[4096];
        int count;
        while ((count = input.read(b)) > -1){
            out.write(b, 0, count);
        }
    }
    public static void sendFile(Socket client, String folder) throws Exception{
        DataInputStream input = new DataInputStream(client.getInputStream());
        setFile(input, folder);
        client.close();
    }
    public static String getName(DataInputStream din) throws IOException {
        byte[] b = haveByte(din);
        return new String(b);
    }
    public static byte[] haveByte(DataInputStream dataInputStream) throws IOException {
        int dataSend = dataInputStream.readInt();
        byte[] bytes = new byte[dataSend];
        dataInputStream.readFully(bytes, 0, dataSend);
        return bytes;
    }
    public void close() throws IOException {
        getServerSocket().close();
    }


    public static void main(String[] args) throws IOException {
        Serveur serveur = new Serveur();
        serveur.openServer(1011);
    }

}