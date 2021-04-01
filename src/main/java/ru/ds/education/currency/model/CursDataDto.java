package ru.ds.education.currency.model;

import javax.persistence.*;
import java.util.Date;

public class CursDataDto {

    private Long id;
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;
    private Double curs;
    private Date cursDate;

    public CursDataDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = CurrencyCode.valueOf(currencyCode);
    }

    public Double getCurs() {
        return curs;
    }

    public void setCurs(Double curs) {
        this.curs = curs;
    }

    public Date getCursDate() {
        return cursDate;
    }

    public void setCursDate(Date cursDate) {
        this.cursDate = cursDate;
    }
}
