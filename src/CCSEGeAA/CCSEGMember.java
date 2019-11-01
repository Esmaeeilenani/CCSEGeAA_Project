package CCSEGeAA;

import java.io.PrintWriter;
import java.util.ArrayList;

public class CCSEGMember {

    private String Name;
    private int ID;
    private int StartDate;
    private ArrayList<Section> Sections;

    public CCSEGMember(String Name, int ID, int StartDate) {
        this.Name = Name;
        this.ID = ID;
        this.StartDate = StartDate;
        Sections = new ArrayList<>();
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getStartDate() {
        return StartDate;
    }

    public void setStartDate(int StartDate) {
        this.StartDate = StartDate;
    }

    public void addSection(Section Sec) {
        Sections.add(Sec);
    }

    public void PrintSection(PrintWriter out) {
        for (int i = 0; i < Sections.size(); i++) {
            System.out.println(Sections.get(i).toString());
        }
    }

    public static CCSEGMember FindMember(ArrayList<CCSEGMember> Member, int ID) {

        for (int i = 0; i < Member.size(); i++) {
            if (Member.get(i).getID() == ID) {
                return Member.get(i);
            }
        }
        return null;
    }
}
