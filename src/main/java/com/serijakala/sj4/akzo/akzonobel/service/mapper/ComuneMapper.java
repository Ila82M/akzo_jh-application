package com.serijakala.sj4.akzo.akzonobel.service.mapper;

import com.serijakala.sj4.akzo.akzonobel.domain.*;
import com.serijakala.sj4.akzo.akzonobel.service.dto.ComuneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Comune and its DTO ComuneDTO.
 */
@Mapper(componentModel = "spring", uses = {ProvinciaMapper.class})
public interface ComuneMapper extends EntityMapper<ComuneDTO, Comune> {

    @Mapping(source = "codProvincia.id", target = "codProvinciaId")
    ComuneDTO toDto(Comune comune);

    @Mapping(source = "codProvinciaId", target = "codProvincia")
    Comune toEntity(ComuneDTO comuneDTO);

    default Comune fromId(Long id) {
        if (id == null) {
            return null;
        }
        Comune comune = new Comune();
        comune.setId(id);
        return comune;
    }
}
