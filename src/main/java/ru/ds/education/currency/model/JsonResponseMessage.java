package ru.ds.education.currency.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.ds.education.currency.controller.CurrencyCbr;

import java.util.List;

@Data
@Getter
@Setter
public class JsonResponseMessage {
    @JsonProperty("onDate")
    private String cursDate;
    @JsonProperty("rates")
    List<CurrencyCbr> currencyCbrs;

    public JsonResponseMessage(String cursDate, List<CurrencyCbr> currencyCbrs) {
        this.cursDate = cursDate;
        this.currencyCbrs = currencyCbrs;
    }
}