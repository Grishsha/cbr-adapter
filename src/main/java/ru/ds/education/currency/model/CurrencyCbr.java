package ru.ds.education.currency.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Getter
@Setter
@NoArgsConstructor
public class CurrencyCbr {
    private String vchCode;
    private BigDecimal vcurs;
}