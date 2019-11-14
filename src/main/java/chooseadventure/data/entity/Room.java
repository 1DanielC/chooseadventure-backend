package chooseadventure.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "room")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Room {

    @Id
    @Column(name = "room_id", insertable = false, updatable = false, nullable = false)
    private Integer id;

    @Column
    private int row;

    @Column
    private int col;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Set<Door> doors;

    public Room(){}

    public Room(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Integer getId() {
        return id;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Set<Door> getDoors() {
        return doors;
    }

    private Map<CardinalDirection, Door> getDoorMap() {
        return doors.stream().collect(Collectors.toMap(Door::getDirection, door -> door));
    }

    public boolean hasDoor(CardinalDirection direction) {
        return doors.stream().anyMatch(door -> door.getDirection() == direction);
    }

    public Door getDoor(CardinalDirection direction) {
        return getDoorMap().get(direction);
    }

    private void setDoors(Set<Door> doors) {
        this.doors = doors;
    }

    public Room toResource() {
        Room room = new Room();
        room.setDoors(doors.stream().map(Door::toResource).collect(Collectors.toSet()));

        return room;
    }
}
