package chooseadventure.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @Column(name = "room_id", insertable = false, updatable = false, nullable = false)
    protected Long id;

    @Column
    private int row;

    @Column
    private int col;

    @OneToMany
    @JoinColumn(name = "room_id")
    private Set<Door> doors;

    public Room(){}

    public Room(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

}
