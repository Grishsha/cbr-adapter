package ru.ds.education.currency.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import ru.cbr.web.GetCursOnDateXMLResponse;
import ru.ds.education.currency.model.CurrencyCbr;

import java.time.LocalDate;

@Component
public class XmlMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory){
        factory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDate.class));
        factory.classMap(GetCursOnDateXMLResponse.GetCursOnDateXMLResult.class, CurrencyCbr.class)
                .byDefault()
                .register();
    }
}
