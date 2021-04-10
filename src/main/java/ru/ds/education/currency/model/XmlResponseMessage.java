package ru.ds.education.currency.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.util.List;

public class XmlResponseMessage {
    /*
     * Vname - Название валюты
     * Vnom - Номинал
     * Vcurs - Курс
     * Vcode - Цифровой код валюты
     * VchCode - Символьный код валюты

     */

    @XmlElement(defaultValue = "onDate")
    private String cursDate;
    @XmlElement(defaultValue = "Vname")
    private String Vname;
    @XmlElement(defaultValue = "Vnom")
    private String Vnom;
    @XmlElement(defaultValue = "VchCode")
    private String VchCode;
    @XmlElement(defaultValue = "Vcode")
    private BigDecimal Vcode;
    @XmlElement(defaultValue = "Vcurs")
    private BigDecimal Vcurs;
}
