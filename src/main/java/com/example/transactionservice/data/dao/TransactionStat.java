package com.example.transactionservice.data.dao;

import com.example.transactionservice.config.AmountConfig;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStat {
    @JsonSerialize(using = AmountConfig.class)
    private BigDecimal sum;
    @JsonSerialize(using = AmountConfig.class)
    private BigDecimal avg;
    @JsonSerialize(using = AmountConfig.class)
    private BigDecimal max;
    @JsonSerialize(using = AmountConfig.class)
    private BigDecimal min;
    private long count;

}
