package com.ungspp1.gadminbackend.api.creditAnalisys.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreditRequestTO{
    private String document;
    private double totalIncome;
    private double debts;
    private double monthlyOutcome;
    private int unemployedFamilyMembers;
    private double familyMembersIncome;
    private Boolean unemployed;
}