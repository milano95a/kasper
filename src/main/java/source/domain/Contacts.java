package source.domain;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class Contacts {

    private User me;

    @ApiModelProperty(notes = "Groups")
    private List<Contact> group;

    @ApiModelProperty(notes = "Connected users")
    private List<Contact> connected;

    @ApiModelProperty(notes = "Users i sent invitation")
    private List<Contact> waiting;

    @ApiModelProperty(notes = "Not connected users")
    private List<Contact> user;

    @ApiModelProperty(notes = "contacts")
    private List<Contact> contact;

    public Contacts(User me, List<Contact> group, List<Contact> connected, List<Contact> user, List<Contact> waiting, List<Contact> contact) {
        this.me = me;
        this.group = group;
        this.connected = connected;
        this.user = user;
        this.waiting = waiting;
        this.contact = contact;
    }

    public User getMe() {
        return me;
    }

    public void setMe(User me) {
        this.me = me;
    }

    public List<Contact> getGroup() {
        return group;
    }

    public void setGroup(List<Contact> group) {
        this.group = group;
    }

    public List<Contact> getConnected() {
        return connected;
    }

    public void setConnected(List<Contact> connected) {
        this.connected = connected;
    }

    public List<Contact> getUser() {
        return user;
    }

    public void setUser(List<Contact> user) {
        this.user = user;
    }

    public List<Contact> getWaiting() {
        return waiting;
    }

    public void setWaiting(List<Contact> waiting) {
        this.waiting = waiting;
    }

    public List<Contact> getContact() {
        return contact;
    }

    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }
}
