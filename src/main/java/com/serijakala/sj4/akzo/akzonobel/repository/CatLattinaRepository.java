package com.serijakala.sj4.akzo.akzonobel.repository;

import com.serijakala.sj4.akzo.akzonobel.domain.CatLattina;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CatLattina entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CatLattinaRepository extends JpaRepository<CatLattina, Long> {

}
