package frame;

import panel.Sendpanel;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class Mafenetre extends JFrame {
    Sendpanel mypanel;
    Socket socket;
    public Sendpanel getMypanel() {
        return mypanel;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setMypanel(Sendpanel mypanel) {
        this.mypanel = mypanel;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Mafenetre(int width, int height, Socket socket) throws IOException {
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setSize(width,height);

        setSocket(socket);
        setMypanel(new Sendpanel(getWidth(), getHeight(), this, getSocket()));
        add(getMypanel());
    }

}
