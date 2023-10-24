package com.spingbootquiz.azizpart.models;

import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Claim implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idClaim;
    private String claimMail;
    private String claimTitle;
    private String claimDetails;

    @Column(columnDefinition = "int default 0")
    private int status;

    private Date claimDate;
    private int claimRating;

    @PrePersist
    public void prePersist() {
        // Mettez la date actuelle comme valeur par défaut
        claimDate = new Date();
    }
}
