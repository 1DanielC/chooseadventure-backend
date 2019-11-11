package chooseadventure.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "door")
public class Door {

    @Id
    @Column(name = "door_id", insertable = false, updatable = false, nullable = false)
    protected Long id;

    private CardinalDirection direction;
}
