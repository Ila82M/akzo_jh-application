package com.serijakala.sj4.akzo.akzonobel.repository;

import com.serijakala.sj4.akzo.akzonobel.domain.CatGruppocolore;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CatGruppocolore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CatGruppocoloreRepository extends JpaRepository<CatGruppocolore, Long> {

}
