package handler;

import frame.Mafenetre;

import java.io.File;
import java.net.Socket;

public class ClientHandler implements Runnable{
    Socket client;
    Mafenetre mf;

    public void setClient(Socket client) {
        this.client = client;
    }

    public void setMf(Mafenetre mf) {
        this.mf = mf;
    }

    public Mafenetre getMf() {
        return mf;
    }

    public Socket getClient() {
        return client;
    }

    public ClientHandler(Socket client, Mafenetre mf){
        setClient(client);
        setMf(mf);
    }
    @Override
    public void run() {
        if (getMf().getMypanel().getFile()!=null){
            System.out.println(getMf().getMypanel().getJTextField().getText());
        }
    }
}
