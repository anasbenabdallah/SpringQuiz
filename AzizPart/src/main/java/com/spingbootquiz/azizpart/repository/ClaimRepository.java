package com.spingbootquiz.azizpart.repository;

import com.spingbootquiz.azizpart.models.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

    List<Claim> findByClaimMailContainingIgnoreCase(String email);
}
