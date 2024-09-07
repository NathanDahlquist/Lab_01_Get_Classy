import java.time.Year;
import java.util.Objects;

public class Person {
    private String firstName;
    private String lastName;
    private String ID;  // should never change sequence of digits
    private String title;  // a prefix: Mr. Mrs. Ms, Prof. Dr. Hon. Etc.
    private int YOB;  // Year of birth  // Range should be 1940 - 2010

    public Person(String firstName, String lastName, String ID, String title, int YOB)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
        this.title = title;
        this.YOB = YOB;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYOB() {
        return YOB;
    }

    public void setYOB(int YOB) {
        this.YOB = YOB;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ID='" + ID + '\'' +
                ", title='" + title + '\'' +
                ", YOB=" + YOB +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return YOB == person.YOB && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(ID, person.ID) && Objects.equals(title, person.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, ID, title, YOB);
    }

    /**
     * Gives first and last name as a string from an object
     * @return string value with first and last name combined
     */
    public String fullName(){
        final char DQ = '\"';

        return(DQ + firstName + " " + lastName + DQ);
    }

    /**
     * Gives a string value with the title, first, and last name of an object
     * @return string with the title, first, and last name of an object
     */
    public String formalName(){
        final char DQ = '\"';

        return(DQ + title + " " + fullName() + DQ);
    }

    /**
     * Gets the current age of a Person object entry using the system date
     * @return a string of the Person object's current age
     */
    public String getAge(){
        int year = Year.now().getValue();
        int age = year-YOB;
        return("" + age);
    }

    /**
     * calculates the age of a person object entry using a provided year
     * @param year
     * @return string of the Person object's age
     */
    public String getAge(int year){
        int age = year-YOB;
        return("" + age);
    }

    /**
     * converts a person object into CVS
     * @return a CVS string of a person object
     */
    public String toCVS(){return(firstName + ", " + lastName + ", " + ID + ", " + title + ", " + YOB);}

    /**
     * converts a person object into JSON
     * @return a JSON string of a person object
     */
    public String toJSON(){return("{\"firstName\":\"" + firstName + "\", \"lastName\":\"" + lastName + "\", \"ID\":\"" + ID + "\", \"title\":\"" + title + "\", \"YOB\":" + YOB + "}");}
}
