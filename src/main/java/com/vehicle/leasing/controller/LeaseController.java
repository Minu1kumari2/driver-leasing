package com.vehicle.leasing.controller;

import com.vehicle.leasing.entity.LeaseAgreement;
import com.vehicle.leasing.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leases")
public class LeaseController {

    @Autowired
    private LeaseService leaseService;

    @PostMapping("/driver/{driverId}/vehicle/{vehicleId}")
    public ResponseEntity<LeaseAgreement> create(
            @PathVariable Long driverId,
            @PathVariable Long vehicleId,
            @RequestBody LeaseAgreement lease) {
        return ResponseEntity.ok(leaseService.createLease(driverId, vehicleId, lease));
    }

    @GetMapping
    public ResponseEntity<List<LeaseAgreement>> getAll() {
        return ResponseEntity.ok(leaseService.getAllLeases());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaseAgreement> getById(@PathVariable Long id) {
        return ResponseEntity.ok(leaseService.getLeaseById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<LeaseAgreement> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(leaseService.updateLeaseStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        leaseService.deleteLease(id);
        return ResponseEntity.ok("Lease deleted");
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<LeaseAgreement>> getByDriver(@PathVariable Long driverId) {
        return ResponseEntity.ok(leaseService.getLeasesByDriver(driverId));
    }
}