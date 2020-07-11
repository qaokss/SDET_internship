package api.tests.model;

import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("first")
    private String firstName;

    @SerializedName("last")
    private String lastName;



    @Override
    public String toString() {
        return  firstName + ' ' + lastName;
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
