package com.angelou.futplayerapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "footballer")
public class Footballer {

    @Id
    @Column(name = "footballer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "common_name")
    private String commonName;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "features")
    private String features;

    @Column(name = "biography", length = 1000) // Adjust the length as needed
    private String biography;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate")
    private Date birthdate;

    @Temporal(TemporalType.DATE)
    @Column(name = "deathdate")
    private Date deathdate;

    @Column(name = "registration_date", nullable = false, updatable = false)
    private Date registrationDate;

    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "position_id")
    private Position position;

    @PrePersist
    protected void onCreate(){
        this.registrationDate = new Date();
        this.lastUpdatedDate = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.lastUpdatedDate = new Date();
    }
}
