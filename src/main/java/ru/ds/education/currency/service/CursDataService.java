package ru.ds.education.currency.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.cbr.web.GetCursOnDateXMLResponse;
import ru.ds.education.currency.model.CurrencyCbr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class CursDataService {

    /**
     * Vname - Название валюты
     * Vnom - Номинал
     * Vcurs - Курс
     * Vcode - Цифровой код валюты
     * VchCode - Символьный код валюты
     */

    public static List<CurrencyCbr> getMappedListOfCurrenciesOnDate(
            GetCursOnDateXMLResponse.GetCursOnDateXMLResult result)
    {
        List<Object> content = result.getContent();
        Element root = (Element) content.get(0);
        NodeList nodes = root.getElementsByTagName("VchCode");
        List<CurrencyCbr> currencies = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            Optional<CurrencyCbr> c = buildCurrency(node);
            c.ifPresent(currencies::add);
        }
        return currencies;
    }

    private static Optional<CurrencyCbr> buildCurrency(Node node) {
        CurrencyCbr currency = new CurrencyCbr();
        Node parent = node.getParentNode();
        NodeList list = parent.getChildNodes();

        for (int j = 0; j < list.getLength(); j++) {

            Node current = list.item(j);

            String name = current.getNodeName().trim();
            Node firstChild = current.getFirstChild();
            String value = firstChild.getNodeValue().trim();

            switch (name) {
                case "Vcurs":
                    currency.setVcurs(new BigDecimal(value));
                    break;
                case "VchCode":
                    currency.setVchCode(value);
                    break;
            }
        }
        return currency.isEmpty() ? Optional.empty() : Optional.of(currency);
    }

    private void initCurrency(CurrencyCbr currency, Node node) {

        Node parent = node.getParentNode();
        NodeList list = parent.getChildNodes();

        for (int j = 0; j < list.getLength(); j++) {

            Node current = list.item(j);

            String name = current.getNodeName().trim();
            Node firstChild = current.getFirstChild();
            String value = firstChild.getNodeValue().trim();

            switch (name) {
                case "Vcurs":
                    currency.setVcurs(new BigDecimal(value));
                    break;
                case "VchCode":
                    currency.setVchCode(value);
                    break;
            }
        }
    }
}
