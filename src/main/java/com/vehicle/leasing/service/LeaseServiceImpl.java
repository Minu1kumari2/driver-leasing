package com.vehicle.leasing.service;

import com.vehicle.leasing.entity.*;
import com.vehicle.leasing.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LeaseServiceImpl implements LeaseService {

    @Autowired private LeaseAgreementRepository leaseRepo;
    @Autowired private DriverRepository driverRepo;
    @Autowired private VehicleRepository vehicleRepo;

    @Override
    public LeaseAgreement createLease(Long driverId, Long vehicleId, LeaseAgreement lease) {
        Driver driver = driverRepo.findById(driverId)
            .orElseThrow(() -> new RuntimeException("Driver not found"));
        Vehicle vehicle = vehicleRepo.findById(vehicleId)
            .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (!vehicle.isAvailable()) {
            throw new RuntimeException("Vehicle is already leased!");
        }

        vehicle.setAvailable(false); // mark as leased
        vehicleRepo.save(vehicle);

        lease.setDriver(driver);
        lease.setVehicle(vehicle);
        lease.setStatus("ACTIVE");
        return leaseRepo.save(lease);
    }

    @Override
    public List<LeaseAgreement> getAllLeases() {
        return leaseRepo.findAll();
    }

    @Override
    public LeaseAgreement getLeaseById(Long id) {
        return leaseRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Lease not found"));
    }

    @Override
    public LeaseAgreement updateLeaseStatus(Long id, String status) {
        LeaseAgreement lease = getLeaseById(id);
        lease.setStatus(status);

        if (status.equals("COMPLETED") || status.equals("CANCELLED")) {
            lease.getVehicle().setAvailable(true); // free the vehicle
            vehicleRepo.save(lease.getVehicle());
        }

        return leaseRepo.save(lease);
    }

    @Override
    public void deleteLease(Long id) {
        leaseRepo.deleteById(id);
    }

    @Override
    public List<LeaseAgreement> getLeasesByDriver(Long driverId) {
        return leaseRepo.findByDriverId(driverId);
    }
}