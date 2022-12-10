package affiche;

import client.Client;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.connectToServer("localhost",1011);
    }
}