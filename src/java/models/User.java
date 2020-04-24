package models;

public class User {
    /* Fields */
    private String firstName;
    private String lastName;
    private String tel;
    private String email;

    /* Constructors */
    public User() {
    }
    public User(String firstName, String lastName, String tel, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tel = tel;
        this.email = email;
    }
    
    /* Getters Setters */
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

    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    /* Methods */
    @Override
    public String toString() {
        return "User{" + "firstName=" + firstName + ", lastName=" + lastName + ", tel=" + tel + ", email=" + email + '}';
    }
}
