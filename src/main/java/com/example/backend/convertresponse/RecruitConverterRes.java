package com.example.backend.convertresponse;


import com.example.backend.dtoresponse.RecruitDTORes;
import com.example.backend.model.RecruitEntity;
import org.springframework.stereotype.Component;

@Component
public class RecruitConverterRes {

    public RecruitDTORes toDTO(RecruitEntity recruitEntity){
        RecruitDTORes recruitDTORes = new RecruitDTORes();
        recruitDTORes.setId(recruitEntity.getId());
        recruitDTORes.setTitle(recruitEntity.getTitle());
        recruitDTORes.setContent(recruitEntity.getContent());
        recruitDTORes.setContentMarkdown(recruitEntity.getContentMarkdown());
        return recruitDTORes;
    }
}
