package com.angelou.futplayerapi.service.impl;

import com.angelou.futplayerapi.model.dao.FootballerDAO;
import com.angelou.futplayerapi.model.dto.FootballerDto;
import com.angelou.futplayerapi.model.entity.Footballer;
import com.angelou.futplayerapi.service.IFootballerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FootballerImplService implements IFootballerService {

    @Autowired // Inyección de dependencias
    private FootballerDAO footballerDao; // Instanciar dao

    @Transactional
    @Override
    public Footballer save(FootballerDto footballerDto) {
        Footballer footballer = Footballer.builder()
                .id(footballerDto.getId())
                .name(footballerDto.getName())
                .lastname(footballerDto.getLastname())
                .features(footballerDto.getFeatures())
                .biography(footballerDto.getBiography())
                .birthdate(footballerDto.getBirthdate())
                .deathdate(footballerDto.getDeathdate())
                .position(footballerDto.getPosition())
                .build();
        return footballerDao.save(footballer);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Footballer> findAll() {
        return (List<Footballer>) footballerDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Footballer findById(Integer id) {
        return footballerDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void deleteFootballer(Footballer footballer) {
        footballerDao.delete(footballer);
    }

    @Override
    public boolean existsById(Integer id) {
        return footballerDao.existsById(id);
    }
}
