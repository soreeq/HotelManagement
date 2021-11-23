package pl.hotelmanagement.rooms;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name= "rooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

/*    @ManyToOne
    private User user;*/

    @NonNull
    private String type;

    @NonNull
    private int size;

    private boolean occupied;

    public Room(String type, int size, boolean occupied) {
        this.type = type;
        this.size = size;
        this.occupied = occupied;
    }
}


