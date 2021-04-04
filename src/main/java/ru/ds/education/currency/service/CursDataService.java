package ru.ds.education.currency.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import ru.ds.education.currency.model.CursDataDto;
import ru.ds.education.currency.model.CursDataEntity;
import ru.ds.education.currency.mapper.CursMapper;
import ru.ds.education.currency.repository.CursDataRepository;
import java.util.List;


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
}