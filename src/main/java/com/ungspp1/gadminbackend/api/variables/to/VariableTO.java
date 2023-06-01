package com.ungspp1.gadminbackend.api.variables.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class VariableTO {
    private String name;
    private float value;
}
