package BowlingGame;
import javax.swing.*;
import java.util.Random;


public class Bowling extends Scoreboard {

    public static void main(String[] args) {
        int rolls; //ROLLS DONE
        int CRS; //CURRENT ROLL SCORE
        int pins = 10; //INITIAL NUMBER OF PINS
        Scoreboard SB = new Scoreboard();

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
        System.out.println("Your total game score is: " + SB.getTotalScore());
    }
}