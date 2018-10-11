package com.serijakala.sj4.akzo.akzonobel.repository;

import com.serijakala.sj4.akzo.akzonobel.domain.CatFamiglia;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CatFamiglia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CatFamigliaRepository extends JpaRepository<CatFamiglia, Long> {

}
