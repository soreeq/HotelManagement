package pl.hotelmanagement.reservations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.hotelmanagement.guests.Guest;
import pl.hotelmanagement.guests.GuestRepository;
import pl.hotelmanagement.rooms.Room;
import pl.hotelmanagement.rooms.RoomRepository;
import pl.hotelmanagement.users.User;
import pl.hotelmanagement.users.UserRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ReservationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/reservations")
    public String getReservations(Model model) {
        List<Reservation> reservationList = reservationRepository.findAll();
        model.addAttribute("reservations", reservationList);
        return "reservations.html";
    }

/*    @PostMapping("/reservations")
    public ResponseEntity addReservation(@RequestHeader String username, @RequestBody Reservation reservation) throws JsonProcessingException {
        User userFromDb = userRepository.findByUsername(username);
        Optional<Room> roomFromDb = roomRepository.findById(reservation.getRoom_id());

*//*        if(userFromDb.isEmpty()){
            return ResponseEntity.ok(HttpStatus.UNPROCESSABLE_ENTITY);
        }*//*

        Reservation newReservation = new Reservation(reservation.getreservation_id(), roomFromDb.get().getId(), reservation.getStartdate(), reservation.getEnddate());

        Reservation savedReservation = reservationRepository.save(newReservation);
        return ResponseEntity.ok(savedReservation);
    }*/
    @ModelAttribute("guests")
    public List<Guest> getGuests() {
        List<Guest> list = guestRepository.findAll();
        return list;
    }

    @ModelAttribute("rooms")
    public List<Room> getRooms() {
        List<Room> list = roomRepository.findAll();
        return list;
    }
    @GetMapping("/add-reservation")
    public String showAddReservationForm(Model model){
        model.addAttribute("newReservation", new Reservation());
        model.addAttribute("startDate", new Date());
        model.addAttribute("endDate", new Date());
        model.addAttribute("standardDate", LocalDateTime.now());
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("timestamp", Instant.now());
        return "addnewreservation.html";
    }
    @PostMapping("/add-reservation")
    public String addReservation(/*@RequestHeader("username") String username,*/ @ModelAttribute Reservation reservation){

        /*        Optional<User> userFromDb = userRepository.findByUsername(username);*//*

        if(userFromDb.isEmpty()){
            return ResponseEntity.ok(HttpStatus.UNPROCESSABLE_ENTITY);
        }*/

        Reservation reservationSaved = new Reservation(reservation.getReservation_id(), reservation.getGuest_id(), reservation.getRoom_id(), reservation.getStartdate(), reservation.getEnddate());
        Reservation save = reservationRepository.save(reservationSaved);

        return "redirect:/reservations";
    }
}
