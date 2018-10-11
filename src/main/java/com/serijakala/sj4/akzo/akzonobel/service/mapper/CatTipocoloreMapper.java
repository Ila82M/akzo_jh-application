package com.serijakala.sj4.akzo.akzonobel.service.mapper;

import com.serijakala.sj4.akzo.akzonobel.domain.*;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatTipocoloreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CatTipocolore and its DTO CatTipocoloreDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CatTipocoloreMapper extends EntityMapper<CatTipocoloreDTO, CatTipocolore> {



    default CatTipocolore fromId(Long id) {
        if (id == null) {
            return null;
        }
        CatTipocolore catTipocolore = new CatTipocolore();
        catTipocolore.setId(id);
        return catTipocolore;
    }
}
