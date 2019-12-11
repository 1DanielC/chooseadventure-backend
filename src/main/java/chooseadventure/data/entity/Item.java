package chooseadventure.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {

    @Id
    @Column(name = "item_id", insertable = false, updatable = false, nullable = false)
    protected Integer id;

    @Column
    private String name;

    @Column
    private String environmentDescription;

    @Column
    private String description;

    @Column
    private boolean hidden;

    public String getName() {
        return name;
    }

    public String getEnvironmentDescription() {
        return environmentDescription;
    }

    public String getDescription() {
        return description;
    }

    public boolean isHidden() {
        return hidden;
    }
}
