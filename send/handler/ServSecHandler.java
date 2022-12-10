package handler;

import serveur.Serveur;

import java.net.Socket;

public class ServSecHandler implements Runnable{
    Socket client;
    String folder;

    public Socket getClient() {
        return client;
    }

    public String getFolder() {
        return folder;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public ServSecHandler(Socket client, String folder) {
        setClient(client);
        setFolder(folder);
    }

    @Override
    public void run() {
        try {
            Serveur.sendFile(getClient(), getFolder());
            System.out.println("tonga");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
