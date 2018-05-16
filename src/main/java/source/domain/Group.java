package source.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

public class Group {

    @ApiModelProperty(notes = "Group details")
    private Family family;

    @ApiModelProperty(notes = "Group members")
    private ArrayList<User> users;

    public Group(Family family, ArrayList<User> users) {
        this.family = family;
        this.users = users;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
