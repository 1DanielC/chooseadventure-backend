package chooseadventure.data.repositories;

import chooseadventure.data.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {


    @Query("SELECT r FROM Room r where r.row = :row and r.col = :col")
    public Optional<Room> getRoomByRowAndCol(@Param("row") int row, @Param("col") int col);
}
