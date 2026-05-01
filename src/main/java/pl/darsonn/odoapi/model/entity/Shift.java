package pl.darsonn.odoapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "shifts")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Column(name = "shift_date", nullable = false)
    private LocalDate shiftDate;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "start_mileage", nullable = false)
    private Integer startMileage;

    @Column(name = "end_time")
    private Instant endTime;

    @Column(name = "end_mileage")
    private Integer endMileage;
}
