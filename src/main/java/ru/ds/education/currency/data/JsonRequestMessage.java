package ru.ds.education.currency.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class JsonRequestMessage {
    @JsonProperty("onDate")
    private String cursDate;

    public JsonRequestMessage(String cursDate) {
        this.cursDate = cursDate;
    }
}