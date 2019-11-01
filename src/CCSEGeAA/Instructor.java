package CCSEGeAA;

public class Instructor extends CCSEGMember {

    private String Qualification;

    public Instructor(String Name, int ID, int StartDate ,String Qualification) {
        super(Name, ID, StartDate);
        this.Qualification = Qualification;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String Qualification) {
        this.Qualification = Qualification;
    }

}
