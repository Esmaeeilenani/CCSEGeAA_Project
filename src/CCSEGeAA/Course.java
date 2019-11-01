package CCSEGeAA;

import java.util.ArrayList;

public class Course {

    private String ID;
    private String Name;
    private int Credit;
    private int Level;
    private boolean theory;
    private boolean Lab;

    public Course(int Level, String ID, String Name, int Credit, String theory, String Lab) {
        this.Level = Level;
        this.ID = ID;
        this.Name = Name;
        this.Credit = Credit;
        this.theory = theory.equals("theory");
        this.Lab = Lab.equals("lab");

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getCredit() {
        return Credit;
    }

    public void setCredit(int Credit) {
        this.Credit = Credit;
    }

    public boolean isTheory() {
        return theory;
    }

    public void setTheory(boolean theory) {
        this.theory = theory;
    }

    public boolean isLab() {
        return Lab;
    }

    public void setLab(boolean Lab) {
        this.Lab = Lab;
    }

    public static Course FindCourse(ArrayList<Course> Courses, String CID) {

        for (int i = 0; i < Courses.size(); i++) {
            
            if (Courses.get(i).getID().equals(CID)) {
               return Courses.get(i);
            }
        }
        
        
        return null;
    }

    @Override

    public String toString() {

        return "";
    }
}
