package com.serijakala.sj4.akzo.akzonobel.repository;

import com.serijakala.sj4.akzo.akzonobel.domain.Regione;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Regione entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegioneRepository extends JpaRepository<Regione, Long> {

}
