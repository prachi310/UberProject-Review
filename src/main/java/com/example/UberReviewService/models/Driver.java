package com.example.UberReviewService.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Driver extends BaseModel{

    private String driverName;

    @Column(nullable = false,unique = true)
    private String licenceNumber;

    //driver has any bookings - oneToMany
    @OneToMany(mappedBy = "driver")
    private List<Booking>bookings = new ArrayList<>() ;
}
