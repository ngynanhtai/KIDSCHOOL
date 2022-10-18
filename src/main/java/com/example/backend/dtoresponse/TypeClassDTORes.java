package com.example.backend.dtoresponse;

import com.example.backend.dto.ClassDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeClassDTORes {
    private Long id;

    private String name;

    private List<ClassDTO> classDTOS;
}
