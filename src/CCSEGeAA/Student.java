package CCSEGeAA;

public class Student extends CCSEGMember {

    private double GPA;
    private int totHours;

    public Student(String Name, int ID, int StartDate, double GPA, int totHours) {
        super(Name, ID, StartDate);
        this.GPA = GPA;
        this.totHours = totHours;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public int getTotHours() {
        return totHours;
    }

    public void setTotHours(int totHours) {
        this.totHours = totHours;
    }

}
