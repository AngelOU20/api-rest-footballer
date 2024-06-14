package com.angelou.futplayerapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "footballer")
public class Footballer {

    @Id
    @Column(name = "footballer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "features")
    private String features;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "deathdate")
    private Date deathdate;

    @Column(name = "registration_date", nullable = false, updatable = false)
    private Date registrationDate;

    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private Position position;
}
