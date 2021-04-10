package ru.ds.education.currency.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import ru.ds.education.currency.model.JsonRequestMessage;
import ru.ds.education.currency.model.JsonResponseMessage;

import java.time.LocalDate;

@Component
public class JsonMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory){
        factory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDate.class));
        factory.classMap(JsonRequestMessage.class, JsonResponseMessage.class)
                .byDefault()
                .register();
    }
}