package com.serijakala.sj4.akzo.akzonobel.repository;

import com.serijakala.sj4.akzo.akzonobel.domain.CatColorebase;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CatColorebase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CatColorebaseRepository extends JpaRepository<CatColorebase, Long> {

}
