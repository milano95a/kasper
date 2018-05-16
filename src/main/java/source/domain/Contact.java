package source.domain;

import io.swagger.annotations.ApiModelProperty;
import source.Type;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class Contact {

    private long Id;

    @ApiModelProperty(notes = "Contact name")
    private String name;

    @ApiModelProperty(notes = "Contact type {group, connected, user, waiting, contact}")
    private Type type;

    public Contact(long id, String name, Type type) {
        Id = id;
        this.name = name;
        this.type = type;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
