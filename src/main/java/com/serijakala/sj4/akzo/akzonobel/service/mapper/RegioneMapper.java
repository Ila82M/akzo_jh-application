package com.serijakala.sj4.akzo.akzonobel.service.mapper;

import com.serijakala.sj4.akzo.akzonobel.domain.*;
import com.serijakala.sj4.akzo.akzonobel.service.dto.RegioneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Regione and its DTO RegioneDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RegioneMapper extends EntityMapper<RegioneDTO, Regione> {



    default Regione fromId(Long id) {
        if (id == null) {
            return null;
        }
        Regione regione = new Regione();
        regione.setId(id);
        return regione;
    }
}
