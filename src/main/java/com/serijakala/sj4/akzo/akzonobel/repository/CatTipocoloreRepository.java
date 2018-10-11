package com.serijakala.sj4.akzo.akzonobel.repository;

import com.serijakala.sj4.akzo.akzonobel.domain.CatTipocolore;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CatTipocolore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CatTipocoloreRepository extends JpaRepository<CatTipocolore, Long> {

}
