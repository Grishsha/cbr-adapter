package ru.ds.education.currency.model;

import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "curs_data")
@Table(name = "curs_data")

public class CursDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private CurrencyCode currencyCode;
    @Column(name = "curs")
    private Double curs;
    @Column(name = "curs_date")
    @Temporal(TemporalType.DATE)
    private Date cursDate;

    public CursDataEntity() {
    }

    public CursDataEntity(CurrencyCode currencyCode, Double curs, Date cursDate) {
        this.currencyCode = CurrencyCode.valueOf(currencyCode.name());
        this.curs = curs;
        this.cursDate = cursDate;
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