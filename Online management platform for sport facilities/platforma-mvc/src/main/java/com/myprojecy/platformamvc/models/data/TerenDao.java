package com.myprojecy.platformamvc.models.data;

import com.myprojecy.platformamvc.models.Teren;
import com.myprojecy.platformamvc.models.TerenIdentity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface TerenDao extends CrudRepository<Teren, TerenIdentity> {
}
