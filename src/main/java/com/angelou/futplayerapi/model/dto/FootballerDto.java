package com.angelou.futplayerapi.model.dto;

import com.angelou.futplayerapi.model.entity.Position;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@ToString
public class FootballerDto implements Serializable {
    private Integer id;

    private String name;

    private String lastname;

    private String commonName;

    private String nickname;

    private String features;

    private String biography;

    private Date birthdate;

    private Date deathdate;

    private Position position;
}
