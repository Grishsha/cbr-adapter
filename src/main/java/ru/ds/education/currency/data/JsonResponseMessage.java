package ru.ds.education.currency.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ds.education.currency.model.CurrencyCbr;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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