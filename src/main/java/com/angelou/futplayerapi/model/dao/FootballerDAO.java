package com.angelou.futplayerapi.model.dao;

import com.angelou.futplayerapi.model.entity.Footballer;
import org.springframework.data.repository.CrudRepository;

public interface FootballerDAO extends CrudRepository<Footballer, Integer> {
}
