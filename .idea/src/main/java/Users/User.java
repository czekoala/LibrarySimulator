package Users;

public abstract class User {

    private String firstName;
    private String lastName;
    private Integer libraryNumber;

    private static Integer numberOfCreatedUsers = 1;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.libraryNumber = numberOfCreatedUsers;
        numberOfCreatedUsers = numberOfCreatedUsers + 1;
    }

    public Integer getLibraryNumber() {
        return libraryNumber;
    }

    public String toString() {
        return this.firstName + ";" + this.lastName + ";" + this.libraryNumber + ";";
    }
}
