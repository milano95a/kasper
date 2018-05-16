package source.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FamilyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @ApiModelProperty(notes = "Group id")
    private long groupId;

    @ApiModelProperty(notes = "Member id")
    private long memberId;

    @ApiModelProperty(notes = "Is member confirmed group invitation")
    private boolean isConfirmed;

    public FamilyMember(long groupId, long memberId, boolean isConfirmed) {
        this.groupId = groupId;
        this.memberId = memberId;
        this.isConfirmed = isConfirmed;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}
