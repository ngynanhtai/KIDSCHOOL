package com.example.backend.dtoresponse;

import com.example.backend.dto.ClassDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LevelClassDTORes {

    private Long id;

    private String name;

    private List<ClassDTO> classDTOS;
}
