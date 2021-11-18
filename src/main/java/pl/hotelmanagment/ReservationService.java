package pl.hotelmanagment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class ReservationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ObjectMapper objectMapper;

/*    @GetMapping("/reservations")
    public ResponseEntity getReservations() throws JsonProcessingException {
    }*/
    @PostMapping("/reservations")
    public ResponseEntity addReservation(@RequestHeader String username, @RequestBody Room room) throws JsonProcessingException {
        Optional<User> userFromDb = userRepository.findByUsername(username);
        Optional<Room> roomFromDb = roomRepository.findById(room.getId());

        Reservation reservation = new Reservation(userFromDb.get().getId(),roomFromDb.get().getId(),);

        if(userFromDb.isEmpty()){
            return ResponseEntity.ok(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return ResponseEntity.ok(objectMapper.writeValueAsString(userFromDb));
    }
}
