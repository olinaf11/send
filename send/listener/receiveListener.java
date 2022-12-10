package listener;

import handler.ClientReceiveHandler;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class receiveListener implements MouseListener {
    SendListener sendListener;
    Socket client;

    public SendListener getSendListener() {
        return sendListener;
    }

    public void setSendListener(SendListener sendListener) {
        this.sendListener = sendListener;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }
    public receiveListener(SendListener sendListener, Socket client){
        setSendListener(sendListener);
        setClient(client);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JLabel jl){
            System.out.println(jl.getText());
            String filename = jl.getText();
            try {
                ObjectOutputStream obj=new ObjectOutputStream(getClient().getOutputStream());
                obj.writeObject(filename);
                obj.flush();
                try {
                    ClientReceiveHandler mythread=new ClientReceiveHandler(getClient());
                    Thread recevoir=new Thread(mythread);
                    recevoir.start();
                } catch (Exception o) {
                    // TODO: handle exception
                    o.printStackTrace();
                }
            } catch (Exception f) {
                // TODO: handle exception
                f.printStackTrace();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
