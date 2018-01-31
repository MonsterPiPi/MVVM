package example.com.mvvm.bean;

/**
 * Created by DELL on 2018/1/28.
 */

public class User {
    private  String firstName;
    private  String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }



    public String getLastName() {
        return lastName;
    }


}
