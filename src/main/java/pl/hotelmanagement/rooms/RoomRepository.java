package pl.hotelmanagement.rooms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {


/*
   @Query("SELECT * FROM Rooms WHERE room_id = :roomid")
   Optional<Room> findByRoomid(@Param("roomid")int Room_id);
*/

  @Transactional
  @Modifying
  @Query(value = "UPDATE rooms c SET c.occupied = :occupied WHERE c.room_id = :roomid", nativeQuery = true)
  void updateStatusByRoomid(@Param("roomid") int room_id, @Param("occupied") String occupied);

  @Transactional
  @Modifying
  @Query(value = "UPDATE rooms c SET c.occupied = :occupied WHERE c.room_id = :roomid", nativeQuery = true)
  void changeStatusOccupied(@Param("roomid") int room_id, @Param("occupied") String isNotOccupied);

  @Query(value = "SELECT * FROM rooms c WHERE c.occupied = :occupied", nativeQuery = true)
  List<Room> freeRooms(@Param("occupied") String occupied);

  @Query(value = "SELECT * FROM rooms c WHERE c.occupied = :occupied", nativeQuery = true)
  List<Room> occupiedRooms(@Param("occupied") String occupied);


  @Transactional
  @Modifying
  @Query(value = "DELETE FROM rooms c WHERE c.room_id = :roomid", nativeQuery = true)
  void deleteByRoomid(@Param("roomid") int room_id);
}
