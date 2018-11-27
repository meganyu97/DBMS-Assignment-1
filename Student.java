
public class Student {

    private int StudentId;
    private String FirstName;
    private String LastName;
    private double GPA;
    private String Major;
    private String FacultyAdvisor;

    public Student(){
        StudentId = 0;
        FirstName = null;
        LastName = null;
        this.GPA = 0.0;
        Major = null;
        FacultyAdvisor = null;
    }

    public Student( String FirstName, String LastName, double GPA, String Major, String FacultyAdvisor) {
        //StudentId = studentId;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.GPA = GPA;
        this.Major = Major;
        this.FacultyAdvisor = FacultyAdvisor;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        LastName = LastName;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public String getFacultyAdvisor() {
        return FacultyAdvisor;
    }

    public void setFacultyAdvisor(String facultyAdvisor) {
        FacultyAdvisor = facultyAdvisor;
    }
}
