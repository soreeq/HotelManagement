package pl.hotelmanagement.guests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.hotelmanagement.reservations.ReservationRepository;
import pl.hotelmanagement.rooms.RoomRepository;
import pl.hotelmanagement.users.User;
import pl.hotelmanagement.users.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class GuestService {
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/guests")
    public String showGuests(Model model) throws JsonProcessingException {
        List<Guest> guestList = guestRepository.findAll();
        System.out.println(guestList);
        model.addAttribute("guests", guestList);
        return "guests";
    }

    @GetMapping("/add-guest")
    public String showAddGuestForm(Model model){
        model.addAttribute("newGuest", new Guest());
        return "addnewguest";
    }

    @PostMapping("/add-guest")
    public String addGuest(@Valid @ModelAttribute("newGuest") Guest guest, BindingResult bindingResult){

/*        Optional<User> userFromDb = userRepository.findByUsername(username);*//*

        if(userFromDb.isEmpty()){
            return ResponseEntity.ok(HttpStatus.UNPROCESSABLE_ENTITY);
        }*/

        List<Guest> guestList = guestRepository.findAll();

        if(bindingResult.hasErrors()) {
            return "addnewguest";
        }


        Guest guestSaved = new Guest(guest.getGuest_id(), guest.getFirstname(), guest.getLastname(), guest.getMiddlename(), guest.getAddress(), guest.getPhonenumber(), guest.getEmail(), guest.getNotes());
        Guest save = guestRepository.save(guestSaved);

        return "redirect:/guests";
    }
    @RequestMapping("/delete-guest/{guest_id}")
    public String deleteGuest(@PathVariable int guest_id){
        guestRepository.deleteByGuestid(guest_id);
        return "redirect:/guests";
    }
}
