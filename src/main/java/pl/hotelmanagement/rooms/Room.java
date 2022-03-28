package pl.hotelmanagement.rooms;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name= "rooms")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Room {
    @Id
    private int room_id;

/*    @ManyToOne
    private User user;*/

    @NonNull
    private String type;

    @NonNull
    private int size;

    private boolean occupied;

    private int rate;

    public Room(int room_id, String type, int size, boolean occupied, int rate)  {
        this.type = type;
        this.size = size;
        this.occupied = occupied;
        this.rate = rate;
        this.room_id = room_id;
    }
}


