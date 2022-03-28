package pl.hotelmanagement.reservations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="reservations")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservation_id;

    private String guest_id;

    private int room_id;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startdate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enddate;

    public Reservation(String guest_id, int room_id,  Date startdate,  Date enddate) {
        this.guest_id = guest_id;
        this.room_id = room_id;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new Java8TimeDialect());
        engine.setTemplateResolver(templateResolver);
        return engine;
    }

}
