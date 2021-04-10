package ru.ds.education.currency.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import ru.cbr.web.DailyInfo;
import ru.cbr.web.DailyInfoSoap;
import ru.cbr.web.GetCursOnDateResponse;
import ru.cbr.web.GetCursOnDateXMLResponse;
import ru.ds.education.currency.mapper.JsonMapper;
import ru.ds.education.currency.mapper.XmlMapper;
import ru.ds.education.currency.model.CurrencyCbr;
import ru.ds.education.currency.model.CursDataDto;
import ru.ds.education.currency.model.JsonRequestMessage;
import ru.ds.education.currency.model.JsonResponseMessage;
import ru.ds.education.currency.service.CursDataService;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
public class JmsController {
    @Autowired
    JsonMapper jsonMapper;
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    XmlMapper xmlMapper;
    @JmsListener(destination = "dev.cbr.request")
    @SendTo("dev.cbr.response")
    public String getMessage(String message) throws DatatypeConfigurationException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonRequestMessage jsonRequestMessage = null;

        List<CurrencyCbr> currencyCbrs;
        DailyInfoSoap dailyInfoSoap = new DailyInfo().getDailyInfoSoap();
        GetCursOnDateResponse.GetCursOnDateResult cursOnDateXMLResult;

        //try {
            //assert false;
            //jsonRequestMessage = objectMapper.readValue(message, JsonRequestMessage.class);
            XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar("2020-04-22");
            cursOnDateXMLResult = dailyInfoSoap.getCursOnDate(xmlGregorianCalendar);

            //currencyCbrs = CursDataService.getMappedListOfCurrenciesOnDate(cursOnDateXMLResult);

        //} catch (DatatypeConfigurationException e) {
        //    log.error("Ошибка при диссериализации", e);
        //    return null;
        //}
        //String response;// = null;
        //JsonResponseMessage jsonResponseMessage = new JsonResponseMessage (jsonRequestMessage.getCursDate(), currencyCbrs);
        //try {
        //return objectMapper.writeValueAsString(jsonResponseMessage);*/

        //jmsTemplate.convertAndSend(objectMapper.writeValueAsString(jsonResponseMessage));
        //jmsTemplate.convertAndSend("tttttt");
        //} catch (JsonProcessingException e) {
        //    log.error("Ошибка при сериализации", e);
        //    return null;
        //}
        //return response;

        return cursOnDateXMLResult.toString();//currencyCbrs.toString();//xmlMapper.mapAsList(currencyCbrs, CurrencyCbr.class).toString();
    }
}
