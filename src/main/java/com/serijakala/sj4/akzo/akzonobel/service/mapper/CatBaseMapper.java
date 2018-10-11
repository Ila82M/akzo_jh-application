package com.serijakala.sj4.akzo.akzonobel.service.mapper;

import com.serijakala.sj4.akzo.akzonobel.domain.*;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatBaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CatBase and its DTO CatBaseDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CatBaseMapper extends EntityMapper<CatBaseDTO, CatBase> {



    default CatBase fromId(Long id) {
        if (id == null) {
            return null;
        }
        CatBase catBase = new CatBase();
        catBase.setId(id);
        return catBase;
    }
}
