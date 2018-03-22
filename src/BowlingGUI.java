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
    int f = 1, pl = 0; //f = FRAME ID, frames iterate from 1 to 10, so initially 1. pl = PLAYER ROW ID, ranging from 0-14 max since there are 2 players for every player and players range from 0 to 7, where Player 0 is actually Player 1.
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
            public void actionPerformed(ActionEvent e) { //When the bowl button is clicked.
                Random RN = new Random();
                CRS = RN.nextInt(pins + 1); //Generate the CURRENT ROLL SCORE [CRS] which is a random number in the range 0 to number of pins e.g. for roll 1 score, 0-10 always.
                pins -= CRS; //Deduct current roll score from total number of pins (initially 10 at the start of a turn)
                pinsField.setText("                Pins: "+ pins); //Display number of pins remaining.
                if (rolls == 1){ //If first roll in process, assign score to roll1 and increment rolls to simulate the second roll next, and vice versa.
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
                    strikepl = pl; // Record strike frame and player.
                    rollStr = "    X"; //Strike represented by an 'X'
                    frameOver = true; //Turn is over, since all pins knocked out by a strike.
                }
                else if ((roll1 + roll2) == 10) { //SPARE
                    spare = true;    
                    sparef = f;
                    sparepl = pl;  //Record spare frame and player.
                    rollStr = "  ";
                    if (roll1 != 0) rollStr += (char) (roll1 + '0'); //Convert roll1 to char and concatenate to the string.
                    else rollStr += '-'; //0 represented by '-'
                    rollStr += "  /"; //Spare represented by '/'
                }
                else { //OPEN
                    rollStr = "  ";
                    if (roll1 != 0) rollStr +=  (char) (roll1 + '0');
                    else rollStr +=  '-';
                    if (roll2 != 0) rollStr += " " + (char) (roll2 + '0');
                    else rollStr += "  -";
                }

                model.setValueAt(rollStr, pl, f); //Set roll score string to scoreTable cell's value in the frame f for player row pl.

                if(frameOver == true) { //After the player's turn is over (current frame ends)
                    Fscores[f][pl / 2] += (roll1 + roll2); //Add sum of rollscores to 2D array for frame scores for the specific player and frame.
                    updateFrameScore(pl, f, Fscores[f][pl / 2]); //Display this frame score onto the score table.


                    if (strike == true && f == (strikef+1) && pl == strikepl) { //IF strike had been made AND it is the next frame from the one in which the strike was made AND is the player's turn who made the strike THEN.

                        Fscores[strikef][strikepl / 2] +=  (roll1 + roll2); //Add score of the 2 new rolls this frame to the strike frame.
                        updateFrameScore(strikepl, (f-1), Fscores[strikef][strikepl / 2]); //Update the strike frame for the player on the table.
                        strike = false;
                    }

                    if (spare == true && f == (sparef+1) && pl == sparepl) { //Similar to the strike conditions

                        Fscores[sparef][sparepl / 2] += roll1; //Add score of the first roll this frame to the spare frame.
                        updateFrameScore(sparepl, (f-1), Fscores[sparef][sparepl / 2]);//Update the spare frame for the player on the table.
                        spare = false;
                    }

                    pl += 2; //Increase player row by 2. (Since 2 rows for every player, and the first row for scores e.g. Player 7 will be row 14)
                    pinsField.setText("                Pins: "+ pins);
                    //Reset all variables to initial values before the next turn.
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
                    BOWLButton.setEnabled(false); //All turns over, bowl button no longer usable.
                    // Calculating total scores for all 8 players for their games.
                    for (int y = 0; y < maxp * 2; y += 2) {
                        int TotalScore = 0;
                        for (int x = 1; x <= 10; x++) {
                            TotalScore += Fscores[x][y/2];
                        }
                        model.setValueAt(String.valueOf(TotalScore), (y + 1), 11); //Display total score for a given player row y in the SECOND ROW (hence y+1) and column 11 ("Total"). 
                        if (TotalScore > WinnerScore){ //Highest Total Score obtained in Winner Score.
                            WinnerScore = TotalScore;
                            WinnerPlayer = (y/2+1); //Since the Player ID 14 (y here) for the row number means Player 7 (so y/2), which should be displayed as Player 8 (so + 1). 
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
                //Set names obtained from the player name fields into the "Players Column" of the table.
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

