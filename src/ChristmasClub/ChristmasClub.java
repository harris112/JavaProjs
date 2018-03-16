package ChristmasClub;

import java.lang.reflect.Member;
import java.util.ArrayList;

/**
 * Created by u1774154 on 13/12/2017.
 */
public class ChristmasClub {

    private ArrayList<Members> MemberList = new ArrayList<Members>();

    private void addMember(Members member) {
        MemberList.add(member);
    }

    private void printMember(Members member) {
        for (Members M : MemberList) {
            if (M == member) {
                System.out.println(M.getName());
                System.out.println(M.getContribution());
            }
        }
    }

    private void printAllMembers() {
        for (Members M : MemberList) {
            printMember(M);
        }
    }

    private int totalContribution() {
        int total = 0;

        for (Members M : MemberList) {
            total += M.getContribution();

        }
        return total;
    }


    public int TotalTurkeys(int turkeyPrice) {
        return totalContribution() / turkeyPrice;
    }


    @Override
    public String toString() {
        return "ChristmasClub{" +
                "MemberList=" + MemberList +
                '}';
    }

    public static void main(String[] args) {

         final int Turkey_price = 10;

        ChristmasClub MyChristmasClub = new ChristmasClub();

        Members gary = new Members("Gary"  );
        Members tony = new Members("Tony");
        Members rubinya = new Members("Rubinya");
        Members steve = new Members("steve");

        MyChristmasClub.addMember(gary);
        MyChristmasClub.addMember(tony);
        MyChristmasClub.addMember(rubinya);
        MyChristmasClub.addMember(steve);

        gary.setContribution(25);
        rubinya.setContribution(22);
        tony.setContribution(20);
        steve.setContribution(18);


        MyChristmasClub.printAllMembers();

        System.out.println("Total contributions made: " + MyChristmasClub.totalContribution());
        System.out.println("Number of turkeys bought : " + MyChristmasClub.TotalTurkeys(10));
    }

    }