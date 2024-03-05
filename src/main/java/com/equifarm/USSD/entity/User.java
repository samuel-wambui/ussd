package com.equifarm.USSD.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UssdUsers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "firstNme")
    private String middleName;
    @Column(name = "lastNme")
    private String lastName;
    @Column(name = "nationalId")
    private long nationalId;

    public int getNationalId() {
        return (int) nationalId;
    }

    @Column(name = "location")
    private String location;


    public int findByNationalId(int id) {
        return (int) nationalId;
    }
}

