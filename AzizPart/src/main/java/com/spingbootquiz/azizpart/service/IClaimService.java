package com.spingbootquiz.azizpart.service;



import com.spingbootquiz.azizpart.models.Claim;

import java.util.List;

public interface IClaimService {


    List<Claim> getAllClaims();

    Claim getClaimById(long id);

    Claim createClaim(Claim claim);


    void deleteClaim(long id);

    Claim searchClaimById(long id);

    Claim updateClaim(long id, Claim updatedClaim);

    List<Claim> searchClaimsByEmail(String email);
}
