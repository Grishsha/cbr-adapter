package ru.ds.education.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ds.education.currency.model.CurrencyCode;
import ru.ds.education.currency.model.CursDataEntity;

import java.util.Date;
import java.util.List;

@Repository
public interface CursDataRepository extends JpaRepository<CursDataEntity,Long>{
    //@Query(value = "SELECT x.* FROM public.curs_data x WHERE x.currency = :currencyCode and x.curs_date = :cursDate", nativeQuery = true)
    //@Query("SELECT c FROM curs_data c WHERE c.currency = :currency and c.curs_date = :curs_date")
    List<CursDataEntity> findByCurrencyCodeAndCursDate (CurrencyCode currencyCode, Date cursDate);
}