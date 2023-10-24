package com.spingbootquiz.azizpart.service;


import com.spingbootquiz.azizpart.models.Claim;
import com.spingbootquiz.azizpart.repository.ClaimRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClaimService implements IClaimService {

    @Autowired
    ClaimRepository claimRepository;
@Override

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }
    @Override
    public Claim getClaimById(long id) {
        return claimRepository.findById(id).orElse(null);
    }
    @Override
    public Claim createClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    @Override
    public void deleteClaim(long id) {
        claimRepository.deleteById(id);
    }
    @Override
    public Claim searchClaimById(long id) {
        return claimRepository.findById(id).orElse(null);
    }
    @Override
    public Claim updateClaim(long id, Claim updatedClaim) {
        Claim existingClaim = claimRepository.findById(id).orElse(null);

        if (existingClaim != null) {
            // Update the properties of the existing claim with the new claim data
            // For example, if you want to update the status:
            existingClaim.setStatus(updatedClaim.getStatus());
            // Update other properties as needed

            // Save the updated claim to the database
            return claimRepository.save(existingClaim);
        } else {
            // Handle the case where the claim with the specified ID does not exist
            return null; // or throw an exception or return an appropriate response
        }
    }
    @Override
    public List<Claim> searchClaimsByEmail(String email) {
        return claimRepository.findByClaimMailContainingIgnoreCase(email);
    }
}
