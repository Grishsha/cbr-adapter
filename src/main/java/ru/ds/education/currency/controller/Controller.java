package ru.ds.education.currency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ds.education.currency.model.CursDataDto;
import ru.ds.education.currency.service.CursDataService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping ("/api")
@Slf4j
public class  Controller {

    @Autowired
    CursDataService cursDataService;

    @RequestMapping(value = "/curs", method = RequestMethod.POST, consumes = "application/json")
    public CursDataDto createCurrencyCursRecord(
                                @RequestParam("currency") String currency,
                                @RequestParam("curs") String curs,
                                @RequestParam("curs_date") String strDate) throws ParseException {
        CursDataDto cursDataDto = new CursDataDto();
        if(!currency.equals("") && !curs.equals("") && !strDate.equals("")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
            Date cursDate = formatter.parse(strDate);
            cursDataDto.setCurrencyCode(currency);
            cursDataDto.setCurs(Double.parseDouble(curs.trim()));
            cursDataDto.setCursDate(cursDate);
            log.debug(cursDataDto.toString());
            return cursDataService.create(cursDataDto);
        }
        log.debug("xbjhbkgkgygk");
        return cursDataDto;
    }

    @RequestMapping(value = "/curs", method = RequestMethod.PUT, consumes = "application/json")
    public CursDataDto updateCurrencyCursRecord(
                                @RequestParam("id") Long id,
                                @RequestParam("currency") String currency,
                                @RequestParam("curs") String curs,
                                @RequestParam("curs_date") String strDate) throws ParseException {
        CursDataDto cursDataDto = new CursDataDto();
        if(!id.toString().equals("") && !currency.equals("") && !curs.toString().equals("") && !strDate.equals("")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
            Date cursDate = formatter.parse(strDate);
            cursDataDto.setId(id);
            cursDataDto.setCurrencyCode(currency);
            cursDataDto.setCurs(Double.parseDouble(curs));
            cursDataDto.setCursDate(cursDate);
            return cursDataService.update(cursDataDto);
        }
        return cursDataDto;
    }

    @RequestMapping(value = "/curs", method = RequestMethod.GET, consumes = "application/json")
    public List<CursDataDto> getCurrencyCursRecord(
                                @RequestParam("currency") String currency,
                                @RequestParam("curs_date") String strDate) throws ParseException {
        CursDataDto cursDataDto = new CursDataDto();
        if(!currency.equals("") && !strDate.equals("")) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
            Date cursDate = formatter.parse(strDate);
            cursDataDto.setCurrencyCode(currency);
            cursDataDto.setCursDate(cursDate);
            return cursDataService.select(cursDataDto);
        }
        return null;
    }

    @RequestMapping(value = "/curs", method = RequestMethod.DELETE, consumes = "application/json")
    public void deleteCurrencyCursRecord(@RequestParam("id") Long id) {
        CursDataDto cursDataDto = new CursDataDto();
        if(!id.toString().equals("")) {
            cursDataDto.setId(id);
            cursDataService.delete(cursDataDto);
        }
    }
}
