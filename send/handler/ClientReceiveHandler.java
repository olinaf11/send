package handler;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;

public class ClientReceiveHandler implements Runnable{
    Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ClientReceiveHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {


            //MAKA ILAY FICHIER NILAINA
            try{
                DataInputStream dataInputStream = new DataInputStream(this.getSocket().getInputStream());
                System.out.println(" hahahaha "+this.getSocket().isClosed());

                int fileNameLength = dataInputStream.readInt();
                byte[] fileNameBytes = new byte[fileNameLength];
                dataInputStream.readFully(fileNameBytes,0,fileNameBytes.length);
                String filename = new String(fileNameBytes);
                int fileContentLength = dataInputStream.readInt();

                byte[] fileContentBytes = new byte[fileContentLength];
                dataInputStream.readFully(fileContentBytes,0,fileContentLength);

                //MAMETRAKA ILAY FICHIER AZO ANATY REPERTOIRE
                File fileToDownload = new File("./FileReceive/"+filename);
                FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload,true);
                fileOutputStream.write(fileContentBytes);
                fileOutputStream.close();
                System.out.println("tongaaaaa");
            }catch (Exception ex){
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
