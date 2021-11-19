package pl.hotelmanagment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        Reservation reservation = new Reservation(1,2, "1998-05-26", "2090-11-11");
        List<User> users = userRepository.findAll();
        model.addAttribute("rezerwacja", reservation);
        model.addAttribute("name", "Przemek");
        model.addAttribute("users", users);
        return "test";
    }

    @PostMapping("/reservations")
    public ResponseEntity addReservation(@RequestHeader String username, @RequestBody Reservation reservation) throws JsonProcessingException {
        Optional<User> userFromDb = userRepository.findByUsername(username);
        Optional<Room> roomFromDb = roomRepository.findById(reservation.getRoom_id());

        if(userFromDb.isEmpty()){
            return ResponseEntity.ok(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Reservation newReservation = new Reservation(userFromDb.get().getId(),roomFromDb.get().getId(), reservation.getStartdate(), reservation.getEnddate());

        Reservation savedReservation = reservationRepository.save(newReservation);
        return ResponseEntity.ok(savedReservation);
    }
}
