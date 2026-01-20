package com.example.mocha.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@Getter
@Setter
@Data
public class TransferDto {
    @NotNull
    private String receiverMatricNo;

    @NotNull
    private BigDecimal amount;
}
