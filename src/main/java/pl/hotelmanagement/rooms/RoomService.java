package pl.hotelmanagement.rooms;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.hotelmanagement.reservations.Reservation;
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
    public String showRooms(Model model) throws JsonProcessingException {
        List<Room> roomList = roomRepository.findAll();
        /*String savedRooms = objectMapper.writeValueAsString(rooms);*/
        model.addAttribute("rooms", roomList);
        return "rooms";
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/add-room")
    public String showAddRoomForm(Model model){
        model.addAttribute("newRoom", new Room());
        return "addnewroom";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add-room")
    public String addRoom(/*@RequestHeader("username") String username,*/ @ModelAttribute Room room) {
/*      User userFromDb = userRepository.findByUsername(username);
      if(userFromDb.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
       }*/
        List<Room> roomList = roomRepository.findAll();


      Room roomSaved = new Room(room.getRoom_id(), room.getType(), room.getSize(), room.getRate());
      roomSaved.setOccupied("Wolny");
      Room save = roomRepository.save(roomSaved);

      return "redirect:/rooms";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/delete-room/{roomid}")
    public String deleteRoom(@PathVariable int roomid){
        roomRepository.deleteByRoomid(roomid);
        return "redirect:/rooms";
    }

    @RequestMapping("/free-rooms")
    public String showFreeRooms(Model model){
        List<Room> listOfFreeRooms = roomRepository.freeRooms("Wolny");
        model.addAttribute("freeRooms", listOfFreeRooms);
        System.out.println(listOfFreeRooms);
        return "freerooms";
    }

    @RequestMapping("/occupied-rooms")
    public String showOccupiedRooms(Model model){
        List<Room> listOfOccupiedRooms = roomRepository.occupiedRooms("Zajete");
        model.addAttribute("occupiedRooms", listOfOccupiedRooms);
        System.out.println(listOfOccupiedRooms);
        return "occupiedrooms";
    }
}