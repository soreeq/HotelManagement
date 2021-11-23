package pl.hotelmanagement.rooms;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.hotelmanagement.users.User;
import pl.hotelmanagement.users.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/rooms")
    public ResponseEntity showRooms() throws JsonProcessingException {
        List<Room> rooms = roomRepository.findAll();
        String savedRooms = objectMapper.writeValueAsString(rooms);
        return ResponseEntity.ok(savedRooms);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/rooms")
    public ResponseEntity addRoom(@RequestHeader("username") String username, @RequestBody Room room) {
      Optional<User> userFromDb = userRepository.findByUsername(username);

      if(userFromDb.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
       }

      Room roomSaved = new Room(room.getType(), room.getSize(), room.isOccupied());
      Room save = roomRepository.save(roomSaved);

      return ResponseEntity.ok(save);
    }
}