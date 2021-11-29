package pl.hotelmanagement.rooms;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findById(int id);
}