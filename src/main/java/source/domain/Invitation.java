package source.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @ApiModelProperty(notes = "Sender user id")
    private long senderId;

    @ApiModelProperty(notes = "Receiver user id")
    private long receiverId;

    @ApiModelProperty(notes = "Status of invitation true = accepted, false = waiting, deleted when rejected")
    private boolean status;

    @ApiModelProperty(notes = "Monitoring start time")
    private int followingFrom;

    @ApiModelProperty(notes = "Monitoring end time")
    private int followingTo;

    @ApiModelProperty(notes = "Invitation created date")
    private long createDate;

    public Invitation(long senderId, long receiverId, boolean status, int followingFrom, int followingTo, long createDate) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = status;
        this.followingFrom = followingFrom;
        this.followingTo = followingTo;
        this.createDate = createDate;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getFollowingFrom() {
        return followingFrom;
    }

    public void setFollowingFrom(int followingFrom) {
        this.followingFrom = followingFrom;
    }

    public int getFollowingTo() {
        return followingTo;
    }

    public void setFollowingTo(int followingTo) {
        this.followingTo = followingTo;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }
}
