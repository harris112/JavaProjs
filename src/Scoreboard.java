package BowlingGame;


public class Scoreboard {
    char[] R1scores = new char[10]; //ROLL 1 SCORES
    char[] R2scores = new char[10]; //ROLL 2 SCORES
    int[] Fscores = new int[10]; //FRAME SCORES


    int roll1, roll2, f;

    public void setRoll1(int r1) {
        this.roll1 = r1;
    }

    public void setRoll2(int r2) {
        this.roll2 = r2;
    }

    public void setFrameID(int frameID) {
        this.f = frameID; //FRAME ID
    }

    public int getTotalScore() {
        int TotalScore = 0;
        for (int i = 0; i < 10; i++){
            TotalScore += Fscores[i];
        }
        return TotalScore;
    }

    public void ProcessScore() {
        Fscores[f] += (roll1 + roll2);

        if (roll1 == 10) {
            R1scores[f] = ' ';
            R2scores[f] = 'X';
            Fscores[f] += 6; //BONUS
        } else if ((roll1 + roll2) == 10) {
            if (roll1 != 0) R1scores[f] = (char) (roll1 + '0');
            else R1scores[f] = '-';
            R2scores[f] = '/';
        } else {
            if (roll1 != 0) R1scores[f] = (char) (roll1 + '0');
            else R1scores[f] = '-';
            if (roll2 != 0) R2scores[f] = (char) (roll2 + '0');
            else R2scores[f] = '-';
        }

        System.out.print(R1scores[f] + " " + R2scores[f] + "\n " + Fscores[f] + "\n\n ");

    }
}