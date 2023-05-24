package com.ungspp1.gadminbackend.api.branch.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.ungspp1.gadminbackend.api.branch.to.OfficeResponseTO;
import com.ungspp1.gadminbackend.api.branch.to.WorkshopResponseTO;
import com.ungspp1.gadminbackend.external.adminArea.to.OfficeTO;
import com.ungspp1.gadminbackend.external.techArea.to.WorkshopTO;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    
    @Mapping(target="workshopName", source="nombre")
    @Mapping(target="workshopCode", source="id_taller", qualifiedByName = "generateWorkshopCode")
    WorkshopResponseTO workshopToResponse(WorkshopTO workshopTO);

    List<WorkshopResponseTO> workshopsToResponseList(List<WorkshopTO> workshopTOs);

    @Mapping(target="officeName", source="nombre")
    @Mapping(target="officeCode", source="id", qualifiedByName = "generateOfficeCode")
    OfficeResponseTO officeToResponse(OfficeTO officeTO);

    List<OfficeResponseTO> officesToResponseList(List<OfficeTO> officeTOs);

    @Named("generateWorkshopCode")
    public static String generateWorkshopCode(int workshopId) {
        String code = workshopId+"";
        while (code.length()<3){
            code = 0+code;
        }
        return "T"+code;
    }

    @Named("generateOfficeCode")
    public static String generateOfficeCode(int officeId) {
        String code = officeId+"";
        while (code.length()<3){
            code = 0+code;
        }
        return "S"+code;
    }
}