package ag;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;
import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by L on 30.05.2016.
 */
public class MWindow extends JFrame {

    public MWindow() {

        setTitle("MWindow");
        setBounds(50, 100, 500, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // setLayout(new GridLayout(2,2));

        JPanel jpUP = new JPanel();
        JPanel jpDOWN = new JPanel();
        add(jpUP, BorderLayout.CENTER);
        add(jpDOWN, BorderLayout.SOUTH);

        jpUP.setLayout(new GridLayout());
        jpDOWN.setLayout(new GridLayout());
        jpUP.setBackground(Color.lightGray);

        JTextArea jTA = new JTextArea();
        JScrollPane jSP = new JScrollPane(jTA);
        jpUP.add(jSP);
        jTA.setBackground(Color.cyan);

        JTextField jT = new JTextField();
        jpDOWN.add(jT);
        jT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //    System.out.println(jT.getText());
                jTA.append(jT.getText() + "\n");
                jT.setText(" ");
            }
        });


        JButton jb = new JButton("Send");
        jpDOWN.add(jb);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // doMyClick();


                //    System.out.println(jT.getText());
                jTA.append(jT.getText() + "\n");
                jT.setText(" ");

                PrintWriter pw = null;
                try {
                    pw = new PrintWriter(new FileWriter("1.txt"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                pw.write((jT.getText() + "\n"));


            }


        });


        MenuBar mb = new MenuBar();
        Menu mFile = new Menu("File");
        Menu mEdit = new Menu("Edit");
        mb.add(mFile);
        mb.add(mEdit);
        MenuItem menuFileEx = new MenuItem("Exit");

        mFile.add(menuFileEx);
        menuFileEx.setShortcut(new MenuShortcut(KeyEvent.VK_A, true));

        menuFileEx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        setMenuBar(mb);


        setVisible(true);


    }
        /*
    private void doMyClick() {

        jTA.append(jT.getText() + "\n");
        jT.setText(" ");

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter("1.txt"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        pw.write((jT.getText() + "\n"));
    }

         */
}
