[ackage BowlingGame;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Random;

public class BowlingGUI extends JFrame {
    private JPanel panel1;
    private JTextField WELCOMETOPINBOWLINGTextField;
    private JButton optionsButton;
    private JButton BOWLButton;
    private JButton SHOWOPTIONSButton;
    private JTable tb1;
    private JTextPane a1TP;
    private JTextPane a2TP;
    private JTextPane a3TP;
    private JTextPane a4TP;
    private JTextPane a5TP;
    private JTextPane a6TP;
    private JTextPane a7TP;
    private JTextPane a8TP;
    private JTextPane a9TP;
    private JTextPane a10TP;
    private JTextPane textPane13;
    private JTextField TOTALSCORETextField;
    private JTextField TF6;
    private JTextField TF7;
    private JTextField TF5;
    private JTextField TF4;
    private JTextField TF8;
    private JTextField TF3;
    private JTextField TF2;
    private JTextField TF1;
    private JLabel lb1;
    private JLabel lb2;
    private JLabel lb3;
    private JLabel lb4;
    private JLabel lb5;
    private JLabel lb6;
    private JLabel lb7;
    private JLabel lb8;
    private JTable table1;
    private Players p;
    private DefaultTableModel model;


    public void setP(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8) {
        p = new Players(p1, p2, p3, p4, p5, p6, p7, p8);
    }


    private BowlingGUI() {
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayerNames Pnames = new PlayerNames(BowlingGUI.this);
                Pnames.setModal(true);
                Pnames.setSize(400, 300);
                Pnames.setVisible(true);
                BOWLButton.setEnabled(true);
                lb1.setText(p.getPlayer1());
                lb2.setText(p.getPlayer2());
                lb3.setText(p.getPlayer3());
                lb4.setText(p.getPlayer4());
                lb5.setText(p.getPlayer5());
                lb6.setText(p.getPlayer6());
                lb7.setText(p.getPlayer7());
                lb8.setText(p.getPlayer8());
            }
        });
        BOWLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Scoreboard SB = new Scoreboard();
                int rolls; //ROLLS DONE
                int CRS; //CURRENT ROLL SCORE
                int pins = 10; //INITIAL NUMBER OF PINS
                for (int f = 0; f < 10; f++) { //FRAME ID
                    SB.setFrameID(f);
                    rolls = 1;
                    pins = 10; //Initial number of pins
                    while (rolls <= 2) {
                        System.out.println("Bowl?");
                        Random RN = new Random();
                        CRS = RN.nextInt(pins + 1);
                        pins -= CRS;
                        System.out.println(CRS);
                        if (rolls == 1) SB.setRoll1(CRS);
                        else SB.setRoll2(CRS);
                        rolls++;
                    }
                    SB.ProcessScore();
                }
                TF1.setText(SB.getTotalScore());
            }
        });
        SHOWOPTIONSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel1, "Players = " + p);

            }
        });


    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("BowlingGUI");
        frame.setContentPane(new BowlingGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

