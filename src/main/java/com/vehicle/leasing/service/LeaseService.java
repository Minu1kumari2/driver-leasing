package com.vehicle.leasing.service;

import com.vehicle.leasing.entity.LeaseAgreement;
import java.util.List;

public interface LeaseService {
    LeaseAgreement createLease(Long driverId, Long vehicleId, LeaseAgreement lease);
    List<LeaseAgreement> getAllLeases();
    LeaseAgreement getLeaseById(Long id);
    LeaseAgreement updateLeaseStatus(Long id, String status);
    void deleteLease(Long id);
    List<LeaseAgreement> getLeasesByDriver(Long driverId);
}