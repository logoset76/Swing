package ag;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;
import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by L on 30.05.2016.
 */
public class MWindow extends JFrame {

    PrintWriter pw = null;
    JTextArea jTA;
    JTextField jTF;


    final String SERVER_ADDR = "localhost";
    final int SERVER_PORT = 8189;
    Socket sock;
    Scanner in;
    PrintWriter out;

    @Override
    public String toString() {
        return "MWindow{" +
                "jtf=" + jTF +
                ", jta=" + jTA +
                ", SERVER_ADDR='" + SERVER_ADDR + '\'' +
                ", SERVER_PORT=" + SERVER_PORT +
                ", sock=" + sock +
                ", in=" + in +
                ", out=" + out +
                '}';
    }

    public MWindow() {

        try {
            sock = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new Scanner(sock.getInputStream());
            out = new PrintWriter(sock.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        try {
            pw = new PrintWriter(new FileWriter("1.txt", true));
        } catch (IOException e1) {
            System.err.println();
        }
        */

        setTitle("MWindow");
        setBounds(50, 100, 500, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // setLayout(new GridLayout(2,2));

        JPanel jpUP = new JPanel();
        JPanel jpDOWN = new JPanel();
        add(jpUP, BorderLayout.CENTER);
        add(jpDOWN, BorderLayout.SOUTH);

        jpUP.setLayout(new GridLayout());
        jpDOWN.setLayout(new BorderLayout());
        jpUP.setBackground(Color.lightGray);

        jTA = new JTextArea();
        JScrollPane jSP = new JScrollPane(jTA);
        jpUP.add(jSP, BorderLayout.CENTER);
        jTA.setBackground(Color.cyan);
        jTA.setEditable(false);
        jTA.setLineWrap(true);

        jTF = new JTextField();
        jpDOWN.add(jTF, BorderLayout.CENTER);
        jTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {doMyClick();}
        });


        JButton jb = new JButton("Send");
        jpDOWN.add(jb, BorderLayout.EAST);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jTF.getText().trim().isEmpty()) {
                    doMyClick();
                    jTF.grabFocus();
                }
            }
        });


        JMenuBar jMB = new JMenuBar();
        JMenu mFile = new JMenu("File");
        JMenu mEdit = new JMenu("Edit");
        jMB.add(mFile);
        jMB.add(mEdit);
        JMenuItem menuFileEx = new JMenuItem("Exit");

        mFile.add(menuFileEx);
      //  menuFileEx.setShortcut(new MenuShortcut(KeyEvent.VK_A, true));
        menuFileEx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
    //            pw.flush();
    //            pw.close();
                System.exit(0);
            }
        });

        setJMenuBar(jMB);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (in.hasNext()) {
                            String w = in.nextLine();
                            if (w.equalsIgnoreCase("end session")) break;
                            jTA.append(w);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }).start();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);

                //           pw.flush();
     //           pw.close();
                try {
                    out.println("end");
                    out.flush();
                    sock.close();
                    out.close();
                    in.close();

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        setVisible(true);

    }

    private void doMyClick() {

        String s = jTF.getText();
        out.println(s);
        out.flush();
 //       pw.println(s);
        jTF.setText(" ");
    }
}


