package SuperLeague;

import java.text.NumberFormat;
import java.util.Locale;


public class Team  {

   private String name;
   private int matchesPlayed;
   private int matchesWon;
   private int matchesDrawn;
   private int matchesLost;
   private int PointsScored;
   private int PointsConceded;
   private int pointDiff;
   private int LeaguePoints;

    public int getLeaguePoints() {
        return LeaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        LeaguePoints = leaguePoints;
    }

    public int getPointDiff() {

        return pointDiff;
    }

    public void setPointDiff(int pointDiff) {
        this.pointDiff = pointDiff;
    }

    public int getMatchesLost() {

        return matchesLost;
    }

    public void setMatchesLost(int matchesLost) {
        this.matchesLost = matchesLost;
    }

    public int getMatchesDrawn() {

        return matchesDrawn;
    }

    public void setMatchesDrawn(int matchesDrawn) {
        this.matchesDrawn = matchesDrawn;
    }

    public int getMatchesWon() {

        return matchesWon;
    }

    public void setMatchesWon(int matchesWon) {
        this.matchesWon = matchesWon;
    }

    public int getMatchesPlayed() {

        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getPointsScored() {
        return PointsScored;
    }

    public void setPointsScored(int pointsScored) {
        PointsScored = pointsScored;
    }

    public int getPointsConceded() {

        return PointsConceded;
    }

    public void setPointsConceded(int pointsConceded) {
        PointsConceded = pointsConceded;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", matchesPlayed=" + matchesPlayed +
                ", matchesWon=" + matchesWon +
                ", matchesDrawn=" + matchesDrawn +
                ", matchesLost=" + matchesLost +
                ", PointsScored=" + PointsScored +
                ", PointsConceded=" + PointsConceded +
                ", pointDiff=" + pointDiff +
                ", LeaguePoints=" + LeaguePoints +
                '}';
    }
}
