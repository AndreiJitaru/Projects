package com.myprojecy.platformamvc.models.data;

import com.myprojecy.platformamvc.models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface ClientDao extends CrudRepository<Client, String> { }
