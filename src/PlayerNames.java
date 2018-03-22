package BowlingGame;

import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerNames extends JDialog  {
    private JPanel contentPane;
    private JTextField p1TF;
    private JTextField p2TF;
    private JTextField p3TF;
    private JTextField p4TF;
    private JButton OKButton;
    private JTextField p5TF;
    private JTextField p6TF;
    private JTextField p7TF;
    private JTextField p8TF;
    private JComboBox maxPlayers;
    private BowlingGUI parent;




    public PlayerNames(BowlingGUI parent) {
        this.parent = parent;
        setContentPane(contentPane);
        setModal(true);


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    onOK();
                    String p1 = p1TF.getText();
                    String p2 = p2TF.getText();
                    String p3 = p3TF.getText();
                    String p4 = p4TF.getText();
                    String p5 = p5TF.getText();
                    String p6 = p6TF.getText();
                    String p7 = p7TF.getText();
                    String p8 = p8TF.getText();
                    parent.setP(p1, p2, p3, p4, p5, p6, p7, p8);
                    dispose();
                }
            }
        });

        maxPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maxpS = (String)maxPlayers.getSelectedItem();
                p2TF.setEnabled(false);
                p3TF.setEnabled(false);
                p4TF.setEnabled(false);
                p5TF.setEnabled(false);
                p6TF.setEnabled(false);
                p7TF.setEnabled(false);
                p8TF.setEnabled(false);

                switch (maxpS){
                    case "2":
                        parent.setMaxp(2);
                        p2TF.setEnabled(true);
                        break;
                    case "3":
                        parent.setMaxp(3);
                        p2TF.setEnabled(true);
                        p3TF.setEnabled(true);
                        break;
                    case "4":
                        parent.setMaxp(4);
                        p2TF.setEnabled(true);
                        p3TF.setEnabled(true);
                        p4TF.setEnabled(true);
                        break;
                    case "5":
                        parent.setMaxp(5);
                        p2TF.setEnabled(true);
                        p3TF.setEnabled(true);
                        p4TF.setEnabled(true);
                        p5TF.setEnabled(true);
                        break;
                    case "6":
                        parent.setMaxp(6);
                        p2TF.setEnabled(true);
                        p3TF.setEnabled(true);
                        p4TF.setEnabled(true);
                        p5TF.setEnabled(true);
                        p6TF.setEnabled(true);
                        break;
                    case "7":
                        parent.setMaxp(7);
                        p2TF.setEnabled(true);
                        p3TF.setEnabled(true);
                        p4TF.setEnabled(true);
                        p5TF.setEnabled(true);
                        p6TF.setEnabled(true);
                        p7TF.setEnabled(true);
                        break;
                    case "8":
                        parent.setMaxp(8);
                        p2TF.setEnabled(true);
                        p3TF.setEnabled(true);
                        p4TF.setEnabled(true);
                        p5TF.setEnabled(true);
                        p6TF.setEnabled(true);
                        p7TF.setEnabled(true);
                        p8TF.setEnabled(true);
                        break;
                }
            }
        });
    }

    private void onOK() {

        // add your code here
        dispose();

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    @Override
    public BowlingGUI getParent() {
        return parent;
    }

    public void setParent(BowlingGUI parent) {
        this.parent = parent;
    }

}
