package pl.hotelmanagment;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="reservations")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private int user_id;

    @NonNull
    private int room_id;

    @NonNull
    @DateTimeFormat
    private LocalDate startDate;

    @NonNull
    @DateTimeFormat
    private LocalDate endDate;

}
