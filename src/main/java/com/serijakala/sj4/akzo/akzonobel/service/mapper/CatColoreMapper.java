package com.serijakala.sj4.akzo.akzonobel.service.mapper;

import com.serijakala.sj4.akzo.akzonobel.domain.*;
import com.serijakala.sj4.akzo.akzonobel.service.dto.CatColoreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CatColore and its DTO CatColoreDTO.
 */
@Mapper(componentModel = "spring", uses = {CatGruppocoloreMapper.class})
public interface CatColoreMapper extends EntityMapper<CatColoreDTO, CatColore> {

    @Mapping(source = "gruppocolore.id", target = "gruppocoloreId")
    CatColoreDTO toDto(CatColore catColore);

    @Mapping(source = "gruppocoloreId", target = "gruppocolore")
    CatColore toEntity(CatColoreDTO catColoreDTO);

    default CatColore fromId(Long id) {
        if (id == null) {
            return null;
        }
        CatColore catColore = new CatColore();
        catColore.setId(id);
        return catColore;
    }
}
