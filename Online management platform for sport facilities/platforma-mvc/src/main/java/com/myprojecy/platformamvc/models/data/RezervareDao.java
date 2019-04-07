package com.myprojecy.platformamvc.models.data;

import com.myprojecy.platformamvc.models.Rezervare;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface RezervareDao extends CrudRepository<Rezervare, Integer>{
}
