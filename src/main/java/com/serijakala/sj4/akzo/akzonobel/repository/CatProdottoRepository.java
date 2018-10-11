package com.serijakala.sj4.akzo.akzonobel.repository;

import com.serijakala.sj4.akzo.akzonobel.domain.CatProdotto;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CatProdotto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CatProdottoRepository extends JpaRepository<CatProdotto, Long> {

}
