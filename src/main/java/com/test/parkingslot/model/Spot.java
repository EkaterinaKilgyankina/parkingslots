package com.test.parkingslot.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "spot", schema = "public")
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String number;
    boolean availableStatus;
}
