package com.spingbootquiz.azizpart.controller;



import com.spingbootquiz.azizpart.models.Claim;
import com.spingbootquiz.azizpart.service.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/claim")
public class ClaimController {

    @Autowired
    private ClaimService claimService;



    @GetMapping("/")
    public List<Claim> getAllClaims() {
        return claimService.getAllClaims();
    }
    // Create a new claim
    @PostMapping("/addClaim")
    public Claim addClaim(@RequestBody Claim claim) {
        return claimService.createClaim(claim);
    }


    // Update the status of a claim by ID
    @PutMapping("/updateStatus/{id}")
    public Claim updateClaimStatus(@PathVariable long id, @RequestParam int status) {
        Claim existingClaim = claimService.getClaimById(id);
            existingClaim.setStatus(status);
            return claimService.updateClaim(id, existingClaim);

    }

    // Delete a claim by ID
    @DeleteMapping("/deleteClaim/{id}")
    public void deleteClaim(@PathVariable long id) {
        claimService.deleteClaim(id);
    }

    // Get a claim by ID
    @GetMapping("/getClaimById/{id}")
    public Claim getClaimById(@PathVariable long id) {
        return claimService.getClaimById(id);
    }

    // Search claims by ID
    @GetMapping("/searchById/{id}")
    public Claim searchClaimById(@PathVariable long id) {
        return claimService.searchClaimById(id);
    }

    @GetMapping("/searchByEmail")
    public List<Claim> searchClaimsByEmail(@RequestParam String email) {
        return claimService.searchClaimsByEmail(email);
    }



}
