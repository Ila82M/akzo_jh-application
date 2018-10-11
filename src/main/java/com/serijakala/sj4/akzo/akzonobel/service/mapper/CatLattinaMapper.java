package com.serijakala.sj4.akzo.akzonobel.service.mapper;

import com.serijakala.sj4.akzo.akzonobel.domain.*;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatLattinaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CatLattina and its DTO CatLattinaDTO.
 */
@Mapper(componentModel = "spring", uses = {CatColorebaseMapper.class})
public interface CatLattinaMapper extends EntityMapper<CatLattinaDTO, CatLattina> {

    @Mapping(source = "colorebase.id", target = "colorebaseId")
    CatLattinaDTO toDto(CatLattina catLattina);

    @Mapping(source = "colorebaseId", target = "colorebase")
    CatLattina toEntity(CatLattinaDTO catLattinaDTO);

    default CatLattina fromId(Long id) {
        if (id == null) {
            return null;
        }
        CatLattina catLattina = new CatLattina();
        catLattina.setId(id);
        return catLattina;
    }
}
