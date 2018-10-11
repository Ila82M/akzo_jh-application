package com.serijakala.sj4.akzo.akzonobel.service.mapper;

import com.serijakala.sj4.akzo.akzonobel.domain.*;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatFamigliaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CatFamiglia and its DTO CatFamigliaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CatFamigliaMapper extends EntityMapper<CatFamigliaDTO, CatFamiglia> {



    default CatFamiglia fromId(Long id) {
        if (id == null) {
            return null;
        }
        CatFamiglia catFamiglia = new CatFamiglia();
        catFamiglia.setId(id);
        return catFamiglia;
    }
}
