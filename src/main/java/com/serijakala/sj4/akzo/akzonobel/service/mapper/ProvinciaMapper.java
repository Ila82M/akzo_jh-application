package com.serijakala.sj4.akzo.akzonobel.service.mapper;

import com.serijakala.sj4.akzo.akzonobel.domain.*;
import com.serijakala.sj4.akzo.akzonobel.service.dto.ProvinciaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Provincia and its DTO ProvinciaDTO.
 */
@Mapper(componentModel = "spring", uses = {RegioneMapper.class})
public interface ProvinciaMapper extends EntityMapper<ProvinciaDTO, Provincia> {

    @Mapping(source = "codRegione.id", target = "codRegioneId")
    ProvinciaDTO toDto(Provincia provincia);

    @Mapping(source = "codRegioneId", target = "codRegione")
    Provincia toEntity(ProvinciaDTO provinciaDTO);

    default Provincia fromId(Long id) {
        if (id == null) {
            return null;
        }
        Provincia provincia = new Provincia();
        provincia.setId(id);
        return provincia;
    }
}
