package handler;

import serveur.Serveur;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerHandler implements Runnable {
    Socket client;
    Serveur serveur;
    boolean first;

    public Socket getClient() {
        return client;
    }

    public Serveur getServeur() {
        return serveur;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public void setServeur(Serveur serveur) {
        this.serveur = serveur;
    }

    public ServerHandler(Serveur serveur, boolean first){
        setServeur(serveur);
        setFirst(true);
    }

    @Override
    public void run() {
        try {
            if (isFirst()){
                System.out.println("client principale entree");
                getServeur().getfirstoption();
                setFirst(false);
            }
            if (getServeur().getOption().compareToIgnoreCase("receive")==0){
                StringBuilder stringBuilder = new StringBuilder("C:/ITU/prog-sys/transfert/send/FileSave");
                ServerReceiveHandler serverReceiveHandler = new ServerReceiveHandler(getServeur(), stringBuilder);
                Thread receive = new Thread(serverReceiveHandler);
                receive.start();
            } else if (getServeur().getOption().compareToIgnoreCase("send")==0) {
                getServeur().sendFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}