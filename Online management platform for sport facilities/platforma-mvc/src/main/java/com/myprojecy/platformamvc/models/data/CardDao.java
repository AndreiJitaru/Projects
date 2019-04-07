package com.myprojecy.platformamvc.models.data;

import com.myprojecy.platformamvc.models.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface CardDao extends CrudRepository<Card, String>{
}
