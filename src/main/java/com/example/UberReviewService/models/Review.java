package com.example.UberReviewService.models;

import com.example.UberReviewService.adapters.CreateReviewDtoToReviewAdapter;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Booking_Review")
public class Review extends BaseModel {


    @Column(nullable = false)
    private String content;

    private double rating;

    @OneToOne( cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private Booking booking;

    @Override
    public String toString(){
        return "Review as" +this.content +" "+this.rating +" "+ this.createdAt;
    }
}
