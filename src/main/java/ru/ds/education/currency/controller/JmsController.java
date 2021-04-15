package ru.ds.education.currency.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import ru.cbr.web.DailyInfo;
import ru.cbr.web.DailyInfoSoap;
import ru.cbr.web.GetCursOnDateXMLResponse;

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
    public String getMessage(String message) throws DatatypeConfigurationException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        //JsonRequestMessage jsonRequestMessage = null;

        //JsonMapper jsonMapper = null;
        List<CurrencyCbr> currencyCbrs;
        DailyInfoSoap dailyInfoSoap = new DailyInfo().getDailyInfoSoap();
        GetCursOnDateXMLResponse.GetCursOnDateXMLResult cursOnDateXMLResult;

        //try {
        //assert false;
        JsonNode node = objectMapper.readTree(message);
        String date = node.get("onDate").asText();

        //jsonRequestMessage = objectMapper.readValue(message, JsonRequestMessage.class);

        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(date);
        xmlGregorianCalendar.setTime(12, 0, 0, 0);
        xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);

        cursOnDateXMLResult = dailyInfoSoap.getCursOnDateXML(xmlGregorianCalendar);

        currencyCbrs = CursDataService.getMappedListOfCurrenciesOnDate(cursOnDateXMLResult);

        //} catch (DatatypeConfigurationException e) {
        //    log.error("Ошибка при диссериализации", e);
        //    return null;
        //}

        //String response;// = null;
        JsonResponseMessage jsonResponseMessage = new JsonResponseMessage (date, currencyCbrs);
        //try {
        return objectMapper.writeValueAsString(jsonResponseMessage);

        //jmsTemplate.convertAndSend(objectMapper.writeValueAsString(jsonResponseMessage));
        //jmsTemplate.convertAndSend("tttttt");
        //} catch (JsonProcessingException e) {
        //    log.error("Ошибка при сериализации", e);
        //    return null;
        //}
        //return response;

        //return currencyCbrs.toString();//currencyCbrs.toString();//xmlMapper.mapAsList(currencyCbrs, CurrencyCbr.class).toString();

    }
}
