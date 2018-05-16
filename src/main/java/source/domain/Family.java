package source.domain;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;

@Entity
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column(name = "family_head", nullable = false)
    @ApiModelProperty(notes = "Owner phone (id)")
    private long familyHead;

    @ApiModelProperty(notes = "Family name")
    @Column(name = "family_name", nullable = true)
    private String familyName;

    @ApiModelProperty(notes = "Family created date")
    @Column(name = "create_date")
    private long createDate;

    public Family(long groupOwner, String groupName, long createDate) {
        this.familyHead = groupOwner;
        this.familyName = groupName;
        this.createDate = createDate;
    }

    public long getId() {
        return Id;
    }

    public long getFamilyHead() {
        return familyHead;
    }

    public void setFamilyHead(long familyHead) {
        this.familyHead = familyHead;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }
}
