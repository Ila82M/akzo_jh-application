package com.serijakala.sj4.akzo.akzonobel.repository;

import com.serijakala.sj4.akzo.akzonobel.domain.CatColore;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CatColore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CatColoreRepository extends JpaRepository<CatColore, Long> {

}
