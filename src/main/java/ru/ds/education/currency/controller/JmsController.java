package ru.ds.education.currency.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.cbr.web.DailyInfo;
import ru.cbr.web.DailyInfoSoap;
import ru.cbr.web.GetCursOnDateXMLResponse;
import ru.ds.education.currency.data.JsonResponseMessage;
import ru.ds.education.currency.model.CurrencyCbr;
import ru.ds.education.currency.service.CursDataService;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

@Controller
@Slf4j
public class JmsController {
    @JmsListener(destination = "dev.cbr.request")
    @SendTo("dev.cbr.response")
    public static String getMessage(String message) throws DatatypeConfigurationException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<CurrencyCbr> currencyCbrs;
        DailyInfoSoap dailyInfoSoap = new DailyInfo().getDailyInfoSoap();
        GetCursOnDateXMLResponse.GetCursOnDateXMLResult cursOnDateXMLResult;

        JsonNode node = objectMapper.readTree(message);
        String date = node.get("onDate").asText();


        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(date);
        xmlGregorianCalendar.setTime(12, 0, 0, 0);
        xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);

        cursOnDateXMLResult = dailyInfoSoap.getCursOnDateXML(xmlGregorianCalendar);

        //XmlMapper xmlMapper = new XmlMapper();
        //currencyCbrs = xmlMapper.readValue(cursOnDateXMLResult, CurrencyCbr.class);
        currencyCbrs = CursDataService.getMappedListOfCurrenciesOnDate(cursOnDateXMLResult);

        JsonResponseMessage jsonResponseMessage = new JsonResponseMessage (date, currencyCbrs);
        return objectMapper.writeValueAsString(jsonResponseMessage);
    }
}
