package source.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class User {

    @ApiModelProperty(notes = "User firstname")
    private String firstname;

    @ApiModelProperty(notes = "User lastname")
    private String lastname;

    @Id
    @ApiModelProperty(notes = "User phone number")
    private long phoneNumber;

    @ApiModelProperty(notes = "User created date")
    private long createDate;

    public User() {}

    public User(String firstname, String lastname, long phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        createDate = new Date().getTime();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String bringFullName(){
        return " " + firstname + " " + lastname + " ";
    }

}
