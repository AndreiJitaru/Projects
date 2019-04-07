package com.myprojecy.platformamvc.models.data;

import com.myprojecy.platformamvc.models.BazaSportiva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface BazaSportivaDao extends CrudRepository<BazaSportiva, Integer> { }
