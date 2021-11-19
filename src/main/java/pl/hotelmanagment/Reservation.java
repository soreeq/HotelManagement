package pl.hotelmanagment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Table(name="reservations")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private int user_id;

    @NonNull
    @JsonProperty("room_id")
    private int room_id;

    @NonNull
    @DateTimeFormat
    @JsonProperty("startdate")
    private String startdate;

    @NonNull
    @DateTimeFormat
    @JsonProperty("enddate")
    private String enddate;

    public Reservation(@NonNull int user_id, @NonNull int room_id, @NonNull String startdate, @NonNull String enddate) {
        this.user_id = user_id;
        this.room_id = room_id;
        this.startdate = startdate;
        this.enddate = enddate;
    }
}
