package com.example.UberReviewService.repositories;

import com.example.UberReviewService.models.Booking;
import com.example.UberReviewService.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {

    @Override
    Optional<Driver> findById(Long id);
    
    Optional<Driver> findByIdAndLicenceNumber(Long id, String licenceNumber);

    @Query(nativeQuery = true, value = "SELECT * FROM Driver WHERE id=:id AND licence_number=:licenceNumber")
    Optional<Driver> rawFindByIdAndLicenceNumber(Long id , String licenceNumber);

    @Query("FROM Driver as d WHERE d.id=:id AND d.licenceNumber=:licenceNumber")
    Optional<Driver> rawFindByIdAndLicence(Long id, String licenceNumber);

    
}
