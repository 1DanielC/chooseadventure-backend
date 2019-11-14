package chooseadventure.data.repository;

import chooseadventure.data.entity.Door;
import chooseadventure.data.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoorRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT d FROM Door d where d.room_id = :id")
    Optional<Door> getDoorByRoomId(@Param("id") long room_id);
}
