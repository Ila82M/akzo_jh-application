package com.serijakala.sj4.akzo.akzonobel.service.mapper;

import com.serijakala.sj4.akzo.akzonobel.domain.*;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatProdottoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CatProdotto and its DTO CatProdottoDTO.
 */
@Mapper(componentModel = "spring", uses = {CatFamigliaMapper.class, CatTipoprodottoMapper.class})
public interface CatProdottoMapper extends EntityMapper<CatProdottoDTO, CatProdotto> {

    @Mapping(source = "famiglia.id", target = "famigliaId")
    @Mapping(source = "tipoprodotto.id", target = "tipoprodottoId")
    CatProdottoDTO toDto(CatProdotto catProdotto);

    @Mapping(source = "famigliaId", target = "famiglia")
    @Mapping(source = "tipoprodottoId", target = "tipoprodotto")
    CatProdotto toEntity(CatProdottoDTO catProdottoDTO);

    default CatProdotto fromId(Long id) {
        if (id == null) {
            return null;
        }
        CatProdotto catProdotto = new CatProdotto();
        catProdotto.setId(id);
        return catProdotto;
    }
}
