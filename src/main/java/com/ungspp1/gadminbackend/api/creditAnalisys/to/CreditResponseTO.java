package com.ungspp1.gadminbackend.api.creditAnalisys.to;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class CreditResponseTO{
    private String document;
    private String resultCredit;
}