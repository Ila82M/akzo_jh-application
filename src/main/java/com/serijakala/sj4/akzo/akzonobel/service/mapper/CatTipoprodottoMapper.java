package com.serijakala.sj4.akzo.akzonobel.service.mapper;

import com.serijakala.sj4.akzo.akzonobel.domain.*;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatTipoprodottoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CatTipoprodotto and its DTO CatTipoprodottoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CatTipoprodottoMapper extends EntityMapper<CatTipoprodottoDTO, CatTipoprodotto> {



    default CatTipoprodotto fromId(Long id) {
        if (id == null) {
            return null;
        }
        CatTipoprodotto catTipoprodotto = new CatTipoprodotto();
        catTipoprodotto.setId(id);
        return catTipoprodotto;
    }
}
