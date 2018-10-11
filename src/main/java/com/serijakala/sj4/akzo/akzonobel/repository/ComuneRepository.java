package com.serijakala.sj4.akzo.akzonobel.repository;

import com.serijakala.sj4.akzo.akzonobel.domain.Comune;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Comune entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComuneRepository extends JpaRepository<Comune, Long> {

}
