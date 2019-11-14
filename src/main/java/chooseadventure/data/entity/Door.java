package chooseadventure.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "door")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Door {

    @Id
    @Column(name = "door_id", insertable = false, updatable = false, nullable = false)
    protected Integer id;

    @Column
    private Long room_id;

    @Column
    @Enumerated(EnumType.STRING)
    private CardinalDirection direction;

    @Column
    private boolean locked;

    public Door() {
    }

    public CardinalDirection getDirection() {
        return direction;
    }

    public boolean isLocked() {
        return locked;
    }

    public Door toResource() {
        Door door = new Door();
        return door;
    }
}
