package panel;

import frame.Mafenetre;
import listener.SendListener;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class Sendpanel extends JPanel {
    JTextField jTextField;
    JLabel jb;
    JButton[] button;
    Mafenetre mf;
    File file;
    Socket socket;

    public JTextField getJTextField() {
        return jTextField;
    }

    public JButton[] getButton() {
        return button;
    }

    public JLabel getJb() {
        return jb;
    }

    public Mafenetre getMf() {
        return mf;
    }

    public File getFile() {
        return file;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setJTextField(JTextField jTextField) {
        this.jTextField = jTextField;
    }

    public void setButton(JButton[] button) {
        this.button = button;
    }

    public void setJb(JLabel jb) {
        this.jb = jb;
    }

    public void setMf(Mafenetre mf) {
        this.mf = mf;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Sendpanel(int width, int height, Mafenetre mf, Socket socket) throws IOException {
        setVisible(true);
        setSize(width, height);
        setLayout(null);

        setSocket(socket);

        setMf(mf);
        setJb(new JLabel("Envoie du fichier :"));
        setButton(new JButton[3]);
        SendListener cl = new SendListener(this, getFile(), getSocket());
        for (int i = 0; i < getButton().length; i++) {
            getButton()[i] = new JButton();
            getButton()[i].addMouseListener(cl);
        }
        getButton()[0].setText("Envoyer");
        getButton()[0].setName("send");
        getButton()[1].setText("Choisir un fichier");
        getButton()[1].setName("choose");
        getButton()[2].setText("Recevoir");
        getButton()[2].setName("receive");

        setJTextField(new JTextField());
        buildPan();

        add(getJb());
        add(getButton()[0]);
        add(getButton()[1]);
        add(getButton()[2]);
        add(getJTextField());
    }
    public void buildPan(){
        int dx = (getWidth()*5)/100, dy = getHeight()/5;
        getJb().setBounds(getWidth()/10, dy, getWidth()/5, getHeight()/15);
        dy = dy+(getHeight()/15);
        getJTextField().setBounds(dx, dy, getWidth()/3, getHeight()/15);
        dx = dx + (getWidth()/3) + 10;
        getButton()[1].setBounds(dx, dy, getWidth()/4, getHeight()/15);
        dy = dy+(getWidth()/15)+10;
        dx = (getWidth()*5)/100;
        getButton()[0].setBounds(dx, dy, getWidth()/5, getHeight()/15);
        dy = dy+(getWidth()/15)+10;
        getButton()[2].setBounds(dx, dy, getWidth()/5, getHeight()/15);
    }
}
