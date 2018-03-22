package BowlingGame;

import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


public class BowlingGUI extends JFrame {
    private JPanel panel1;
    private JTextField WELCOMETOPINBOWLINGTextField;
    private JButton SetPlayersBtn;
    private JButton BOWLButton;
    private JButton RevealWinnerBtn;
    private Players p;

    public JTable scoreTable;
    private JScrollPane RevealWinner;
    private JTextField pinsField;
    public DefaultTableModel model = (DefaultTableModel) scoreTable.getModel();
    public int WinnerScore = 0;
    public int WinnerPlayer = 0;
    char[][] R1scores = new char[11][8]; //ROLL 1 SCORES
    char[][] R2scores = new char[11][8]; //ROLL 2 SCORES
    int[][] Fscores = new int[11][8]; //FRAME SCORES
    int roll1 = 0, roll2 = 0;
    int rolls = 1; //ROLLS DONE
    int CRS; //CURRENT ROLL SCORE
    int pins = 10; //INITIAL NUMBER OF PINS
    int f = 1, pl = 0;
    boolean frameOver = false;
    boolean strike = false;
    int strikef = 0, strikepl = 0;
    boolean spare = false;
    int sparef = 0, sparepl = 0;
    String rollStr = "  ";
    int maxp = 2;

    // setter for the players from the player names class.
    public void setP(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8) {
        p = new Players(p1, p2, p3, p4, p5, p6, p7, p8);
    }

    public void setMaxp(int n){
        this.maxp = n;
    }

    public BowlingGUI() {
        BOWLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random RN = new Random();
                CRS = RN.nextInt(pins + 1);
                pins -= CRS;
                pinsField.setText("                Pins: "+ pins);
                if (rolls == 1){
                    roll1 = CRS;
                    rolls++;
                }
                else {
                    roll2 = CRS;
                    rolls = 1;
                    frameOver = true;
                }

                if (roll1 == 10) { //STRIKE
                    strike = true;
                    strikef = f;
                    strikepl = pl;
                    rollStr = "    X";
                    frameOver = true;
                }
                else if ((roll1 + roll2) == 10) { //SPARE
                    spare = true;
                    sparef = f;
                    sparepl = pl;
                    rollStr = "  ";
                    if (roll1 != 0) rollStr += (char) (roll1 + '0');
                    else rollStr += '-';
                    rollStr += "  /";
                }
                else { //OPEN
                    rollStr = "  ";
                    if (roll1 != 0) rollStr +=  (char) (roll1 + '0');
                    else rollStr +=  '-';
                    if (roll2 != 0) rollStr += " " + (char) (roll2 + '0');
                    else rollStr += "  -";
                }

                 model.setValueAt(rollStr, pl, f);

                if(frameOver == true) {
                    Fscores[f][pl / 2] += (roll1 + roll2);
                    updateFrameScore(pl, f, Fscores[f][pl / 2]);


                    if (strike == true && f == (strikef+1) && pl == strikepl) {

                        Fscores[strikef][strikepl / 2] +=  (roll1 + roll2);
                        updateFrameScore(strikepl, (f-1), Fscores[strikef][strikepl / 2]);
                        strike = false;
                    }

                    if (spare == true && f == (sparef+1) && pl == sparepl) {


                        Fscores[sparef][sparepl / 2] += roll1;
                        updateFrameScore(sparepl, (f-1), Fscores[sparef][sparepl / 2]);
                        spare = false;
                    }

                    pl += 2;
                    pinsField.setText("                Pins: "+ pins);
                    pins = 10;
                    frameOver = false;
                    roll1 = 0;
                    roll2 = 0;
                    rollStr = "  ";
                }

                if(pl==maxp*2){
                    f++;
                    pl = 0;
                }

                if (f == 11){
                    BOWLButton.setEnabled(false);
                    // getting total scores for all 8 players for their games.
                    for (int y = 0; y < maxp * 2; y += 2) {
                        int TotalScore = 0;
                        for (int x = 1; x <= 10; x++) {
                            TotalScore += Fscores[x][y/2];
                        }
                        // setting the calculated values for total scores and assigning the winner and his score.
                        model.setValueAt(String.valueOf(TotalScore), (y + 1), 11);
                        if (TotalScore > WinnerScore){
                            WinnerScore = TotalScore;
                            WinnerPlayer = (y/2+1);
                        }
                    }
                    RevealWinnerBtn.setEnabled(true);
                }

            }
        });

        SetPlayersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayerNames Pnames = new PlayerNames(BowlingGUI.this);
                Pnames.setModal(true);
                Pnames.setSize(400, 300);
                Pnames.setVisible(true);
                BOWLButton.setEnabled(true);
                model.setValueAt(p.getPlayer1(), 0, 0);
                model.setValueAt(p.getPlayer2(), 2, 0);
                model.setValueAt(p.getPlayer3(), 4, 0);
                model.setValueAt(p.getPlayer4(), 6, 0);
                model.setValueAt(p.getPlayer5(), 8, 0);
                model.setValueAt(p.getPlayer6(), 10, 0);
                model.setValueAt(p.getPlayer7(), 12, 0);
                model.setValueAt(p.getPlayer8(), 14, 0);
            }
        });

        RevealWinnerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel1, " Winner of this game is Player No. : " + ((WinnerPlayer)) + " Points scored : " + (WinnerScore));
            }
        });


    }


    private void updateFrameScore(int p, int f, int fs) {
        String spaces = "     ";
        if (fs>=10) spaces = "   ";
        model.setValueAt((spaces + fs), (p + 1), f);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Bowling GUI");
        frame.setContentPane(new BowlingGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1100, 340);
        frame.setVisible(true);

        System.out.print("Now starting a new game!\n");
        //EMPTY ALL LABELS TO DEFAULT

    }

    private void createUIComponents() {
        String[] columns = {"Players","1", "2", "3","4","5","6","7","8","9","10","Total"};
        String[][] data = {{}};

        model = new DefaultTableModel(data, columns);

        scoreTable = new JTable(model){
            public boolean isCellEditable(int data, int columns)
            {
                return false;
            }

            public Component prepareRenderer(TableCellRenderer r, int data, int columns) {
                Component color = super.prepareRenderer(r, data, columns);
                //0, 1  4,5   8,9  12,13
                if (data % 4 == 0 || (data-1) % 4 == 0) {
                    color.setBackground(Color.WHITE);
                    color.setForeground(Color.BLACK);
                }
                else {
                    color.setBackground(Color.GRAY);
                    color.setForeground(Color.WHITE);
                }
                return color;
            }
        };



        //ROW 0 already in place
        //INITIALIZING ADDING EMPTY ROWS
        for (int i = 1; i < 16; i++) {
            model.addRow(new Object[]{});
        }
    }
}

