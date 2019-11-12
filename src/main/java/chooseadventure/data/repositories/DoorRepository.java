package chooseadventure.data.repositories.impl;

import chooseadventure.data.entities.Door;
import chooseadventure.data.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoorRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT d FROM Door d where d.room_id = :id")
    public Optional<Door> getDoorByRoomId(@Param("room_id") long room_id);
}
