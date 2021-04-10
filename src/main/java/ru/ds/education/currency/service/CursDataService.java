package ru.ds.education.currency.service;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.cbr.web.GetCursOnDateXMLResponse;
import ru.ds.education.currency.model.CurrencyCbr;
import ru.ds.education.currency.model.CursDataDto;
import ru.ds.education.currency.model.CursDataEntity;
import ru.ds.education.currency.mapper.CursMapper;
import ru.ds.education.currency.repository.CursDataRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@NoArgsConstructor
public class CursDataService {

    @Autowired
    CursDataRepository cursDataRepository;
    @Autowired
    CursMapper cursMapper;
    @Autowired
    CursDataEntity cursDataEntity;

    public CursDataDto create(CursDataDto cursDataDto){
        cursDataEntity = cursMapper.map(cursDataDto, CursDataEntity.class);
        cursDataRepository.save(cursDataEntity);

        return cursMapper.map(cursDataEntity, CursDataDto.class);
    }

    public CursDataDto update(CursDataDto cursDataDto){
        cursDataEntity = cursMapper.map(cursDataDto, CursDataEntity.class);
        cursDataRepository.save(cursDataEntity);
        cursDataEntity = cursDataRepository.getOne(cursDataEntity.getId());
        return cursMapper.map(cursDataEntity, CursDataDto.class);
    }

    public List<CursDataDto> select(CursDataDto cursDataDto){
        cursDataEntity = cursMapper.map(cursDataDto, CursDataEntity.class);
        List<CursDataEntity> cursDataEntityList= cursDataRepository.findByCurrencyCodeAndCursDate(
                cursDataEntity.getCurrencyCode(),
                cursDataEntity.getCursDate());

        return cursMapper.mapAsList(cursDataEntityList, CursDataDto.class);
    }

    public void delete(CursDataDto cursDataDto){
        cursDataEntity = cursMapper.map(cursDataDto, CursDataEntity.class);
        if(cursDataRepository.existsById(cursDataEntity.getId())) {
            cursDataEntity = cursDataRepository.getOne(cursDataEntity.getId());
            cursDataRepository.delete(cursDataEntity);
        }
    }

    public static List<CurrencyCbr> getMappedListOfCurrenciesOnDate(GetCursOnDateXMLResponse.GetCursOnDateXMLResult result)
    {
        List<Object> content = result.getContent();
        ElementNSImpl root = (ElementNSImpl) content.get(0);
        NodeList nodes = root.getElementsByTagName("VchCode");
        List<CurrencyCbr> currencies = new ArrayList<>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            Optional<CurrencyCbr> c = buildCurrency(node);
            c.ifPresent(currencyCbr -> currencies.add(currencyCbr));
        }
        return currencies;
    }

    private static Optional<CurrencyCbr> buildCurrency(Node node) {
        CurrencyCbr currencyCbr = new CurrencyCbr();
        Node parent = node.getParentNode();
        NodeList list = parent.getChildNodes();

        for (int j = 0; j < list.getLength(); j++) {

            Node current = list.item(j);

            String name = current.getNodeName().trim();
            Node firstChild = current.getFirstChild();
            String value = firstChild.getNodeValue().trim();

            switch (name) {
                case "Vcurs":
                    currencyCbr.setVcurs(new BigDecimal(value));//currencyCbr.vcurs = new BigDecimal(value);
                    break;
                case "VchCode":
                    currencyCbr.setVchCode(value);//currencyCbr.vchCode = value;
                    break;
            }
        }
        if (currencyCbr.equals(""))
            return Optional.empty();
        else
            return Optional.of(currencyCbr);
    }
}