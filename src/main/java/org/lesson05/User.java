public class User {
    private String msisdn;
    private String firstName;
    private String lastName;

    // Конструкторы, геттеры и сеттеры
    public User(String msisdn, String firstName, String lastName) {
        this.msisdn = msisdn;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
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
}
