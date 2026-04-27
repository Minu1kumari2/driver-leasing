package com.vehicle.leasing.repository;

import com.vehicle.leasing.entity.LeaseAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeaseAgreementRepository extends JpaRepository<LeaseAgreement, Long> {
    List<LeaseAgreement> findByDriverId(Long driverId);
    List<LeaseAgreement> findByVehicleId(Long vehicleId);
}