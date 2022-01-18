package pl.hotelmanagement.reservations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.hotelmanagement.rooms.Room;
import pl.hotelmanagement.rooms.RoomRepository;
import pl.hotelmanagement.users.User;
import pl.hotelmanagement.users.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class ReservationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/reservations")
    public String getReservations(Model model) {

        return "dashboard";
    }

    @PostMapping("/reservations")
    public ResponseEntity addReservation(@RequestHeader String username, @RequestBody Reservation reservation) throws JsonProcessingException {
        Optional<User> userFromDb = userRepository.findByUsername(username);
        Optional<Room> roomFromDb = roomRepository.findById(reservation.getRoom_id());

        if(userFromDb.isEmpty()){
            return ResponseEntity.ok(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Reservation newReservation = new Reservation(reservation.getGuest_id(), roomFromDb.get().getId(), reservation.getStartdate(), reservation.getEnddate());

        Reservation savedReservation = reservationRepository.save(newReservation);
        return ResponseEntity.ok(savedReservation);
    }
}
