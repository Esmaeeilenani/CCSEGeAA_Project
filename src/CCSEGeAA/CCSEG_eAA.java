package CCSEGeAA;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CCSEG_eAA {
    //Create Arrays of Object

    private static ArrayList<Course> CourseList = new ArrayList<>();
    private static ArrayList<CCSEGMember> MemberList = new ArrayList<>();
    private static ArrayList<Section> SectionList = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Run();

    }

    public static void Run() throws FileNotFoundException {

        File FR = new File("inCCSEGeaa.txt");
        //Check if the file Exists
        if (!FR.exists()) {
            System.out.println("File not Found>>>");
            System.exit(0);
        }

        Scanner in = new Scanner(FR);
        PrintWriter out = new PrintWriter("outCCSEGeaa.txt");
        ReadFile(in, out);
    }
//------------------------------------------------------------------------------

    //to Read File Content
    public static void ReadFile(Scanner in, PrintWriter out) {

        while (in.hasNext()) {
            String Command = in.next().trim();

            switch (Command) {

                case "InputDepartmentPlan":
                    Dep_Plan(in, out);
                    break;

                case "InputInstructorData":

                    InsData(in, out);
                    break;

                case "InputStudentData":
                    StuData(in, out);
                    break;

                case "InputSectionData":
                    SecData(in, out);
                    break;

                case "RegisterCourse":
                    RegisterCourseStu(in, out);
                    break;

                case "InstructorLoadRequest":
                    RegisterCourseIns(in, out);
                    break;

                case "PrintAllInstructorsLoads":
                    PrintInsLoad(out);
                    break;

                case "PrintSectionStudentList":
                    PrintEachSec(in, out);
                    break;

            }
            System.out.println("============================================================");
        }

        out.flush();
        out.close();
        System.exit(0);
    }
//------------------------------------------------------------------------------

    //add Courses Plan Details to the System
    public static void Dep_Plan(Scanner in, PrintWriter out) {

        //Number of Courses must Storde in the System
        int numOfCourses = in.nextInt();

        int LevelNum = 0;

        //to add all Courses to the System
        for (int i = 0; i < numOfCourses;) {

            //Split INFO
            String[] Line = in.nextLine().trim().split("\\s+");

            if (Line.length > 2) {
                CourseList.add(new Course(LevelNum, Line[0] + " " + Line[1], Line[2].replaceAll("\\?", " "), Integer.parseInt(Line[3].trim()),
                        Line[4], Line[5]));
                i++;
            } else if (Line.length == 2) {
                //Line [1] have the Level Number 
                LevelNum = Integer.parseInt(Line[1].trim());
            }

        }

        System.out.println("College of Computer Science & Engineering Academic Affairs Electronic  Department Plan(Courses) Data has been Inserted");
    }
//------------------------------------------------------------------------------

    //add Instructor INFO to the System
    public static void InsData(Scanner in, PrintWriter out) {

        //Number of Instructor will be Storde in the System
        int numIns = in.nextInt();

        for (int i = 0; i < numIns; i++) {
            MemberList.add(new Instructor(in.next().replaceAll("\\?", " "), in.nextInt(), in.nextInt(), in.next().replaceAll("\\?", " ")));
        }

        System.out.println("Department Instructor's Data has been Inserted");
    }
//------------------------------------------------------------------------------

    //add Student INFO to the System
    public static void StuData(Scanner in, PrintWriter out) {

        //Number of Student Storde in the System
        int numOfSut = in.nextInt();
        for (int i = 0; i < numOfSut; i++) {
            MemberList.add(new Student(in.next().replaceAll("\\?", " "), in.nextInt(), in.nextInt(), in.nextDouble(), in.nextInt()));

            in.next();
        }

        System.out.println("Department Students Data has been Inserted");
    }
//------------------------------------------------------------------------------

    //add Section INFO to the System 
    public static void SecData(Scanner in, PrintWriter out) {
        //Number of Section that will Storde in the System
        int NumOfSec = in.nextInt();
        for (int i = 0; i < NumOfSec;) {
            String[] Line = in.nextLine().trim().split("\\s+");

            if (Line.length > 1) {
                //Find the Course to Assign it to Section
                Course Cou = Course.FindCourse(CourseList, Line[2] + " " + Line[3]);

                SectionList.add(new Section(Line[0].trim(), Line[1], Cou, Integer.parseInt(Line[4]), Line[5].replaceAll("\\?", " "), Line[6].replaceAll("\\?", " "), Line[7], Line[8]));
                i++;
            }

        }
        System.out.println("Department Schedule (Sections) has been Inserted");
    }
//------------------------------------------------------------------------------

    //Assign Course to Student
    public static void RegisterCourseStu(Scanner in, PrintWriter out) {
        // Number of Student tha want to Register Courses
        int numOfRegister = in.nextInt();

        for (int i = 0; i < numOfRegister;) {
            // Store the Student with the ID
            int ID = in.nextInt();

            //Search For Student with givin ID
            Student Stu = (Student) CCSEGMember.FindMember(MemberList, ID);

            //if Student Found
            if (Stu != null) {

                String Line[] = in.nextLine().trim().split("\\s+");

                //Current Credit Hours
                int Hours = 0;

                //if the Student have more then 100 Hours he must Add this Course
                boolean CCCS411 = false;

                //Error message
                String Error = "";

                String INFO = "";

                for (int j = 0; j < Line.length - 1; j++) {

                    Section Sec = Section.FindSection(SectionList, Line[j].trim());

                    //total Credit hours for this semmester
                    Hours += Sec.getCourse().getCredit();

                    if (Sec.getCourse().getID().equals("CCCS 411")) {
                        CCCS411 = true;
                    }

                    INFO += Sec.toString() + "\r\n";
                    Stu.addSection(Sec);
                    Sec.addStudent(Stu);
                }

                //Message For Student with Less then 12 Hourse
                Error = (Hours < 12) ? "Student Registered Credit is less than 12 Credits Must ADD courses\r\n" : "";

                //if the Student Total Cridet 100 or more and didnt Register CCCS 411
                Error += (Stu.getTotHours() >= 100 && CCCS411 == false) ? "Student is a Graduate and did not Register CCCS 411\r\n" : "";

                System.out.println("Student: " + Stu.getName() + "   stdID: " + Stu.getID() + "  Total Credit Hours: " + Hours);
                System.out.println(FormatOut());
                System.out.println(INFO);
                System.out.println("__________________________________________________________");
                System.out.println("Registration Validity Report\r\n");
                System.out.print(Error);
                System.out.println("----------------------------------------------------------\r\n");

                i++;
            } else {
                System.out.println("No Student with this ID " + ID);

            }

        }

    }
//------------------------------------------------------------------------------    

    //Assign Course to Instructor
    public static void RegisterCourseIns(Scanner in, PrintWriter out) {
        System.out.println("All Instructors Load List \n"
                + "_________________________________\r\n");
        //Numberr of Instructor
        int numOfIns = in.nextInt();

        for (int i = 0; i < numOfIns; i++) {

            Instructor Ins = (Instructor) CCSEGMember.FindMember(MemberList, in.nextInt());
            String Line[] = in.nextLine().trim().split("\\s+");

            for (int j = 0; j < Line.length - 1; j++) {

                Section Sec = Section.FindSection(SectionList, Line[j]);
                Ins.addSection(Sec);
                Sec.addInstructor(Ins);

            }

        }

    }
//------------------------------------------------------------------------------    

    //Print all Instructor Sections
    public static void PrintInsLoad(PrintWriter out) {
        for (CCSEGMember Member : MemberList) {
            if (Member instanceof Instructor) {
                Instructor Ins = (Instructor) Member;
                System.out.println("Instructor: " + Ins.getName() + " Instructor ID: " + Ins.getID());
                System.out.println(FormatOut());
                Ins.PrintSection(out);
            }
        }

    }
//------------------------------------------------------------------------------ 

    //Print Student in each Section
    public static void PrintEachSec(Scanner in, PrintWriter out) {
        String ID = in.next();
        while (!ID.equals("-1")) {

            Section Sec = Section.FindSection(SectionList, ID);
            System.out.println("Course: " + Sec.getCourse().getName() + " " + Sec.getCourse().getID() + "                 Section: " + Sec.getName() + "\r\n"
                    + "Day and Time: " + Sec.getTheoryTime() + " " + Sec.getLabTime() + "\r\n"
                    + "____________________________________________________");
            Sec.PrintStudent(out);
            System.out.println("");
            ID = in.next().trim();
        }

    }

    public static String FormatOut() {

        return String.format("%-20s %-20s %-20s %-32s %s ", "SecId", "SecName", "Course", "Time", "Location");
    }
//------------------------------------------------------------------------------    
}
