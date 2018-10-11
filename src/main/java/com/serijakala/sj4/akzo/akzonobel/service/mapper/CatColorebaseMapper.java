package com.serijakala.sj4.akzo.akzonobel.service.mapper;

import com.serijakala.sj4.akzo.akzonobel.domain.*;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatColorebaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CatColorebase and its DTO CatColorebaseDTO.
 */
@Mapper(componentModel = "spring", uses = {CatBaseMapper.class, CatGruppocoloreMapper.class, CatTipocoloreMapper.class})
public interface CatColorebaseMapper extends EntityMapper<CatColorebaseDTO, CatColorebase> {

    @Mapping(source = "base.id", target = "baseId")
    @Mapping(source = "gruppocolore.id", target = "gruppocoloreId")
    @Mapping(source = "tipocolore.id", target = "tipocoloreId")
    CatColorebaseDTO toDto(CatColorebase catColorebase);

    @Mapping(source = "baseId", target = "base")
    @Mapping(source = "gruppocoloreId", target = "gruppocolore")
    @Mapping(source = "tipocoloreId", target = "tipocolore")
    CatColorebase toEntity(CatColorebaseDTO catColorebaseDTO);

    default CatColorebase fromId(Long id) {
        if (id == null) {
            return null;
        }
        CatColorebase catColorebase = new CatColorebase();
        catColorebase.setId(id);
        return catColorebase;
    }
}
