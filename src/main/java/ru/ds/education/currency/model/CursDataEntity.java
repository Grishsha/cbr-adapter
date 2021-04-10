package ru.ds.education.currency.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
import ru.ds.education.currency.model.CurrencyCode;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "curs_data")
@Table(name = "curs_data")
@Getter
@Setter
@Builder
@Component
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

    public CursDataEntity(){

    }

    public CursDataEntity(CurrencyCode currencyCode, Date cursDate) {
        this.currencyCode = currencyCode;
        this.cursDate = cursDate;
    }

    public CursDataEntity(CurrencyCode currencyCode, Double curs, Date cursDate) {
        this.currencyCode = currencyCode;
        this.curs = curs;
        this.cursDate = cursDate;
    }

    public CursDataEntity(Long id, CurrencyCode currencyCode, Double curs, Date cursDate) {
        this.currencyCode = currencyCode;
        this.curs = curs;
        this.cursDate = cursDate;
    }
}