package com.serijakala.sj4.akzo.akzonobel.repository;

import com.serijakala.sj4.akzo.akzonobel.domain.CatBase;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CatBase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CatBaseRepository extends JpaRepository<CatBase, Long> {

}
