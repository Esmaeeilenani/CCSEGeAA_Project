package CCSEGeAA;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Section {

    private String ID;
    private String Name;
    private Course course;
    private int Size;
    private String theoryTime;
    private String LabTime;
    private String RoomNum;
    private String LabNum;
    private ArrayList<Student> StuList;
    private ArrayList<Instructor> InsList;

    public Section(String ID, String Name, Course course, int Size, String theoryTime, String LabTime, String RoomNum, String LabNum) {
        this.ID = ID;
        this.Name = Name;
        this.course = course;
        this.Size = Size;
        this.theoryTime = theoryTime;
        this.LabTime = LabTime;
        this.RoomNum = RoomNum;
        this.LabNum = LabNum;
        this.StuList = new ArrayList<>();
        this.InsList = new ArrayList<>();
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int Size) {
        this.Size = Size;
    }

    public String getTheoryTime() {
        return theoryTime;
    }

    public void setTheoryTime(String theoryTime) {
        this.theoryTime = theoryTime;
    }

    public String getLabTime() {
        return LabTime;
    }

    public void setLabTime(String LabTime) {
        this.LabTime = LabTime;
    }

    public String getRoomNum() {
        return RoomNum;
    }

    public void setRoomNum(String RoomNum) {
        this.RoomNum = RoomNum;
    }

    public String getLabNum() {
        return LabNum;
    }

    public void setLabNum(String LabNum) {
        this.LabNum = LabNum;
    }

    public ArrayList<Student> getStuList() {
        return StuList;
    }

    public void addStudent(Student Stu) {
        this.StuList.add(Stu);
    }

    public ArrayList<Instructor> getInsList() {
        return InsList;
    }

    public void addInstructor(Instructor Ins) {
        this.InsList.add(Ins);
    }

    public static Section FindSection(ArrayList<Section> SecList, String ID) {

        for (int i = 0; i < SecList.size(); i++) {
            if (SecList.get(i).getID().trim().equals(ID.trim())) {
                return SecList.get(i);
            }
        }

        return null;
    }

    public void PrintStudent(PrintWriter out) {
        for (Student Stu : StuList) {
            System.out.println(Stu.getID() + " " + Stu.getName());
        }

    }

    @Override
    public String toString() {

        return String.format("%-20s %-20s %-20s %-10s %-21s %-1s %s", ID, Name, course.getID(), theoryTime, LabTime, RoomNum, LabNum);
    }
}
