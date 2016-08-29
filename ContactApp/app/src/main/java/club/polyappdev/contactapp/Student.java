package club.polyappdev.contactapp;


public class Student {


    //TODO: Add comments
    private String firstName;
    private String lastName;
    private String email;
    private boolean InterestedAndroid;
    private boolean InterestediOS;
    private boolean InterestedWindows;
    private String pizza;
    private String year;
    private String soda;

    //need an empty constructor
    public Student() {
        //empty constructor
    }

    /**
     * Constuctor
     * @param firstName
     * @param lastName
     * @param email
     * @param iiOS
     * @param iAndroid
     * @param iWindows
     * @param pizza
     * @param soda
     * @param year
     */
    public Student(String firstName, String lastName, String email, boolean iAndroid, boolean iiOS, boolean iWindows,
                   String pizza, String soda, String year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.InterestedAndroid = iAndroid;
        this.InterestediOS = iiOS;
        this.InterestedWindows = iWindows;
        this.pizza = pizza;
        this.soda = soda;
        this.year = year;
    }


    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean getInterestedWindows() {
        return this.InterestedWindows;
    }

    public boolean getInterestedAndroid() {
        return this.InterestedAndroid;
    }

    public boolean getInterestediOS() {
        return this.InterestediOS;
    }

    public String getPizza() {
        return this.pizza;
    }

    public String getSoda() {
        return this.soda;
    }

    public String getYear() {
        return this.year;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setInterestedWindows(boolean interest){
        this.InterestedWindows = interest;
    }

    public void setInterestedAndroid(boolean interest){
        this.InterestedAndroid = interest;
    }

    public void setInterestediOS(boolean interest) {
        this.InterestediOS = interest;
    }

    public void setPizza(String pizza) {
        this.pizza = pizza;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setSoda(String soda) {
        this.soda = soda;
    }
}
