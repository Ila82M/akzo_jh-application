package com.serijakala.sj4.akzo.akzonobel.service.mapper;

import com.serijakala.sj4.akzo.akzonobel.domain.*;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatGruppocoloreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CatGruppocolore and its DTO CatGruppocoloreDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CatGruppocoloreMapper extends EntityMapper<CatGruppocoloreDTO, CatGruppocolore> {



    default CatGruppocolore fromId(Long id) {
        if (id == null) {
            return null;
        }
        CatGruppocolore catGruppocolore = new CatGruppocolore();
        catGruppocolore.setId(id);
        return catGruppocolore;
    }
}
