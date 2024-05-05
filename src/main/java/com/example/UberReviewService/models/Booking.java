package com.example.UberReviewService.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Booking extends BaseModel{

    @Enumerated(value = EnumType.STRING) // store enum as string if we choose ordinal it will be stored as number starts from0,1,2...
    private BookingStatus bookingStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    private Long totalDistance;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;
    @ManyToOne(fetch = FetchType.LAZY)
    private Passenger passenger;
}
