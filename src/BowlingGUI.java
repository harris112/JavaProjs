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
    private JPanel panel1; //Main content panel
    private JTextField WELCOMETOPINBOWLINGTextField;
    private JButton SetPlayersBtn; //Opens dialog box for setting max number of players and player names.
    private JButton BOWLButton; //Used to simulate bowls, and handle all main operations.
    private JButton RevealWinnerBtn; //Reveals the winner player with his score in a dialog box after all turns end.
    private Players p; //Object containing all player name strings.
    
    public JTable scoreTable;
    private JScrollPane RevealWinner;
    private JTextField pinsField;
    public DefaultTableModel model = (DefaultTableModel) scoreTable.getModel();
    public int WinnerScore = 0; //The score of the winner player. (Highest Total Score stored here)
    public int WinnerPlayer = 0; //Winner Player ID (0-7) where Player 0 is actually Player 1.
    int[][] Fscores = new int[11][8]; //2D ARRAY STORING FRAME SCORES FOR MAX 8 PLAYERS
    int roll1 = 0, roll2 = 0; //ROLL SCORES FOR EACH ROLL/BOWL
    int rolls = 1; //ROLLS/BOWLS DONE
    int CRS; //CURRENT ROLL SCORE
    int pins = 10; //INITIAL NUMBER OF PINS
    int f = 1, pl = 0; //f = FRAME ID, frames iterate from 1 to 10, so initially 1. pl = PLAYER ID, players range from 0 to 7, where Player 0 is actually Player 1.
    boolean frameOver = false; //To check when a PLAYER'S TURN WITHIN A SPECIFIC FRAME IS OVER.
    boolean strike = false; //To check for strikes.
    int strikef = 0, strikepl = 0; //Variables to record the frame and player who made a strike.
    boolean spare = false; //To check for spares.
    int sparef = 0, sparepl = 0; //Variables to record the frame and player who made a spare.
    String rollStr = "  "; //Displayed on Jtable, used later after concatenation with roll1 and roll2 scores.
    int maxp = 2; //MAXIMUM NUMBER OF PLAYERS

    // setter for the players from the PlayerNames class.
    public void setP(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8) {
        p = new Players(p1, p2, p3, p4, p5, p6, p7, p8);
    }
    // setter for the maximum number of players from the PlayerNames class.
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
        String spaces = "     "; //Spaces for formatting.
        if (fs>=10) spaces = "   ";
        model.setValueAt((spaces + fs), (p + 1), f); //Print final frame score string with spaces onto the SECOND ROW from the first player row p (p+1 hence) and frame f. 
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Bowling GUI");
        frame.setContentPane(new BowlingGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1100, 340); //preferred size of window frame
        frame.setVisible(true);
    }

    private void createUIComponents() {
        String[] columns = {"Players","1", "2", "3","4","5","6","7","8","9","10","Total"}; //Column Headers (12 columns total)
        String[][] data = {{}}; //2D string array for score data, initially empty.
        
        model = new DefaultTableModel(data, columns); //New scoreTable model according to these rows and columns

        scoreTable = new JTable(model){
            public boolean isCellEditable(int data, int columns) //Make cells of the jtable non-editable. They will only display scores.
            {
                return false;
            }

            public Component prepareRenderer(TableCellRenderer r, int data, int columns) {  //Using a TableCellRenderer to edit properties for cell background and foreground color
                Component color = super.prepareRenderer(r, data, columns);
                /*Switch background and foreground colors for every 2 data rows for this score sheet table.
                This is done so that it is easier to distinguish the 2 rows for every player separately. */
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
        //INITIALIZING BY ADDING EMPTY ROWS
        for (int i = 1; i < 16; i++) {
            model.addRow(new Object[]{});
        }
    }
}

