package com.serijakala.sj4.akzo.akzonobel.repository;

import com.serijakala.sj4.akzo.akzonobel.domain.CatTipoprodotto;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CatTipoprodotto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CatTipoprodottoRepository extends JpaRepository<CatTipoprodotto, Long> {

}
