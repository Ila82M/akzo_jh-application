package com.serijakala.sj4.akzo.akzonobel.service.mapper;

import com.serijakala.sj4.akzo.akzonobel.domain.*;
import com.serijakala.sj4.akzo.akzonobel.service.dto.ManagerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Manager and its DTO ManagerDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ManagerMapper extends EntityMapper<ManagerDTO, Manager> {



    default Manager fromId(Long id) {
        if (id == null) {
            return null;
        }
        Manager manager = new Manager();
        manager.setId(id);
        return manager;
    }
}
