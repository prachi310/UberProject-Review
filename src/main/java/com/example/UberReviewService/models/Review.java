package com.example.UberReviewService.models;

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

    @Override
    public String toString(){
        return "Review as" +this.content +" "+this.rating +" "+ this.createdAt;
    }
}
