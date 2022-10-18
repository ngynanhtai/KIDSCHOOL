package com.example.backend.converter;

import com.example.backend.dto.LevelClassDTO;
import com.example.backend.model.LevelClassEntity;
import org.springframework.stereotype.Component;

@Component
public class LevelClassConverter {

    public LevelClassDTO toDTO(LevelClassEntity entity) {
        LevelClassDTO result = new LevelClassDTO();
        result.setId(entity.getId());
        result.setName(entity.getName());
        return result;
    }

    public LevelClassEntity toEntity(LevelClassDTO dto) {
        LevelClassEntity result = new LevelClassEntity();
        result.setId(dto.getId());
        result.setName(dto.getName());
        return result;
    }
    public LevelClassEntity toEntity(LevelClassEntity result, LevelClassDTO dto) {
        result.setName(dto.getName());
        return result;
    }
}
