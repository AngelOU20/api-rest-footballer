package com.angelou.futplayerapi.service;

import com.angelou.futplayerapi.model.dto.FootballerDto;
import com.angelou.futplayerapi.model.entity.Footballer;

import java.util.List;

public interface IFootballerService {
    Footballer save(FootballerDto footballerDto);

    List<Footballer> findAll();

    Footballer findById(Integer id);

    void deleteFootballer(Footballer footballer);

    boolean existsById(Integer id);
}
