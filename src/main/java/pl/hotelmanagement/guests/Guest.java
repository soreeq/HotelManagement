package pl.hotelmanagement.guests;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="guests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Guest {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int guest_id;
    private String firstname;
    private String lastname;
    private String middlename;
    private String address;
    private int phonenumber;
    private String notes;
    private String email;
}
