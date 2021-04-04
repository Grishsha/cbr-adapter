package ru.ds.education.currency.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.util.Date;

@Component
@Getter
@Setter
public class CursDataDto {

    @ApiModelProperty("Id записи")
    private Long id;
    @ApiModelProperty("Код валюты")
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;
    @ApiModelProperty("Курс валюты")
    private Double curs;
    @ApiModelProperty("Дата")
    private Date cursDate;

    public CursDataDto(){

    }

    public CursDataDto(Long id){
        this.id = id;
    }

    public CursDataDto(CurrencyCode currencyCode, Date cursDate) {
        this.currencyCode = currencyCode;
        this.cursDate = cursDate;
    }

    public CursDataDto(CurrencyCode currencyCode, Double curs, Date cursDate) {
        this.currencyCode = currencyCode;
        this.curs = curs;
        this.cursDate = cursDate;
    }

    public CursDataDto(Long id, CurrencyCode currencyCode, Double curs, Date cursDate) {
        this.id = id;
        this.currencyCode = currencyCode;
        this.curs = curs;
        this.cursDate = cursDate;
    }
}
