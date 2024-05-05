package com.example.UberReviewService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Passenger extends BaseModel{

    private String name;

    @OneToMany(mappedBy = "passenger",fetch = FetchType.LAZY)
    private List<Booking>bookings =new ArrayList<>();
}

