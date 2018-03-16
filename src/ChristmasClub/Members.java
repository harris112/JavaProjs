package ChristmasClub;

/**
 * Created by u1774154 on 13/12/2017.
 */
public class Members {

    private String name;
    public int contribution;

    public Members(String name) {
        this.name = name;
    }

    public Members(int contribution) {

        this.contribution = contribution;
    }

    public int getContribution() {

        return contribution;
    }

    public void setContribution(int contribution) {
        this.contribution = contribution;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Members{" +
                "contriubution=" + contribution +
                ", name='" + name + '\'' +
                '}';
    }
}
