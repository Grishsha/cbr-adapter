package ru.ds.education.currency.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class JsonResponseMessage {
    @JsonProperty("onDate")
    private String cursDate;
    @JsonProperty("rates")
    List<CurrencyCbr> currencyCbrs;
}