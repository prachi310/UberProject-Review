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

    Optional<Driver> findByIdAndLicenseNumber(Long id, String licenseNumber);

    @Query(nativeQuery = true, value = "SELECT * FROM Driver WHERE id=:id AND license_number=:licenseNumber")
    Optional<Driver> rawFindByIdAndLicenseNumber(Long id , String licenseNumber);

    @Query("FROM Driver as d WHERE d.id=:id AND d.licenseNumber=:licenseNumber")
    Optional<Driver> rawFindByIdAndLicense(Long id, String licenseNumber);

    List<Driver> findAllByIdIn(List<Long> driverIds);




}
