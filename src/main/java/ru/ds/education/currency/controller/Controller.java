package ru.ds.education.currency.controller;

import io.swagger.annotations.ApiOperation;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ds.education.currency.model.CurrencyCode;
import ru.ds.education.currency.model.CursDataDto;
import ru.ds.education.currency.service.CursDataService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping ("/api")
@NoArgsConstructor
public class Controller {

    @Autowired
    CursDataService cursDataService;
    @Autowired
    CursDataDto cursDataDto;
    @ApiOperation(value = "Создать запись в базе курс валюты на дату")
    @RequestMapping(value = "/curs", method = RequestMethod.POST, consumes = "application/json")
    public CursDataDto createCurrencyCursRecord(
            @NonNull @RequestParam(value = "currency") String currencyCode,
            @NonNull @RequestParam(value = "curs") Double curs,
            @NonNull @RequestParam(value = "curs_date") String str) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
        Date cursDate = formatter.parse(str);

        cursDataDto = new CursDataDto(
                            CurrencyCode.valueOf(currencyCode.trim()),
                            curs,
                            cursDate);
        return cursDataService.create(cursDataDto);
    }

    @ApiOperation(value = "Изменить запись в базе курс валюты на дату")
    @RequestMapping(value = "/curs", method = RequestMethod.PUT, consumes = "application/json")
    public CursDataDto updateCurrencyCursRecord(
            @NonNull @RequestParam("id") Long id,
            @NonNull @RequestParam("currency") String currencyCode,
            @NonNull @RequestParam("curs") Double curs,
            @NonNull @RequestParam("curs_date") String str) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
        Date cursDate = formatter.parse(str);

        cursDataDto = new CursDataDto(
                            id,
                            CurrencyCode.valueOf(currencyCode.trim()),
                            curs,
                            cursDate);
        return cursDataService.update(cursDataDto);
    }

    @ApiOperation(value = "Получить запись в базе курс валюты на дату")
    @RequestMapping(value = "/curs", method = RequestMethod.GET, consumes = "application/json")
    public List<CursDataDto> getCurrencyCursRecord(
            @NonNull @RequestParam("currency") String currencyCode,
            @NonNull @RequestParam("curs_date") String str) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
        Date cursDate = formatter.parse(str);

        cursDataDto = new CursDataDto(
                            CurrencyCode.valueOf(currencyCode.trim()),
                            cursDate);
        return cursDataService.select(cursDataDto);
    }

    @ApiOperation(value = "Удалить запись в базе курс валюты на дату")
    @RequestMapping(value = "/curs", method = RequestMethod.DELETE, consumes = "application/json")
    public String deleteCurrencyCursRecord(
            @NonNull @RequestParam("id") Long id) {
        cursDataDto = new CursDataDto(id);
        cursDataService.delete(cursDataDto);
        return String.valueOf(id);
    }
}

