package ru.ds.education.currency.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import ru.ds.education.currency.model.CursDataDto;
import ru.ds.education.currency.model.CursDataEntity;

@Component
public class CursMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory){

        factory.classMap(CursDataDto.class, CursDataEntity.class)
                                    .byDefault()
                                    .register();
    }
}
