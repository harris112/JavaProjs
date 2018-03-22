package BowlingGame;

public class Players {

    public String Player1, Player2, Player3, Player4, Player5, Player6, Player7, Player8;


    public Players(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8) {
        Player1 = p1;
        Player2 = p2;
        Player3 = p3;
        Player4 = p4;
        Player5 = p5;
        Player6 = p6;
        Player7 = p7;
        Player8 = p8;
    }
    public String getPlayer8() {
        return Player8;
    }

    public void setPlayer8(String player8) {
        Player8 = player8;
    }

    public String getPlayer7() {
        return Player7;
    }

    public void setPlayer7(String player7) {
        Player7 = player7;
    }

    public String getPlayer6() {
        return Player6;
    }

    public void setPlayer6(String player6) {
        Player6 = player6;
    }

    public String getPlayer5() {
        return Player5;
    }

    public void setPlayer5(String player5) {
        Player5 = player5;
    }

    public String getPlayer4() {
        return Player4;
    }

    public void setPlayer4(String player4) {
        Player4 = player4;
    }

    public String getPlayer3() {
        return Player3;
    }

    public void setPlayer3(String player3) {
        Player3 = player3;
    }

    public String getPlayer2() {
        return Player2;
    }

    public void setPlayer2(String player2) {
        Player2 = player2;
    }

    public String getPlayer1() {
        return Player1;
    }

    public void setPlayer1(String player1) {
        Player1 = player1;
    }

    @Override
    public String toString() {
        return "Players{" +
                "Player1='" + Player1 + '\'' +
                ", Player2='" + Player2 + '\'' +
                ", Player3='" + Player3 + '\'' +
                ", Player4='" + Player4 + '\'' +
                ", Player5='" + Player5 + '\'' +
                ", Player6='" + Player6 + '\'' +
                ", Player7='" + Player7 + '\'' +
                ", Player8='" + Player8 + '\'' +
                '}';
    }
}

