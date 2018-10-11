package com.serijakala.sj4.akzo.akzonobel.repository;

import com.serijakala.sj4.akzo.akzonobel.domain.Provincia;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Provincia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

}
