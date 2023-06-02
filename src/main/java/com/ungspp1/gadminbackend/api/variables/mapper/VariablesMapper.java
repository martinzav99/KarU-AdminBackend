package com.ungspp1.gadminbackend.api.variables.mapper;
import java.util.List;

import org.mapstruct.Mapper;

import com.ungspp1.gadminbackend.api.variables.to.VariableTO;
import com.ungspp1.gadminbackend.model.entity.VariableDE;

@Mapper(componentModel = "spring")
public interface VariablesMapper {

    VariableTO deToTO(VariableDE variableDE);

    List<VariableTO> deListToToList(List<VariableDE> variables);

}
