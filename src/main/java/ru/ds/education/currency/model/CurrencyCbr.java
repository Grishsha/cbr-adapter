package ru.ds.education.currency.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
@JacksonXmlRootElement(localName="CurrencyCbr")
public class CurrencyCbr {
    @JacksonXmlProperty(localName="vchCode")
    @JsonProperty("currency")
    private String vchCode;
    @JacksonXmlProperty(localName="vcurs")
    @JsonProperty("curs")
    private BigDecimal vcurs;

    public Boolean isEmpty() {
        return vchCode.isEmpty() || vcurs == null;
    }
}