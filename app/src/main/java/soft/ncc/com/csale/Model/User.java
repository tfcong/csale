package soft.ncc.com.csale.Model;

import java.io.Serializable;

/**
 * Created by phamthanhcong on 08/11/2017.
 */

public class User implements Serializable {
    String name,pass,email,address,phoneNumber,birthDate,gender;
    public User() {
    }

    public User(String name, String pass, String email, String address, String phoneNumber, String birthDate, String gender) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

