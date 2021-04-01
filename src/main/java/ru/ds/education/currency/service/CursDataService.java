package ru.ds.education.currency.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import ru.ds.education.currency.model.CursDataDto;
import ru.ds.education.currency.model.CursDataEntity;
import ru.ds.education.currency.repository.CursDataRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CursDataService {

    @Autowired
    CursDataRepository cursDataRepository;

    public CursDataService() {
    }

    public CursDataDto create(CursDataDto cursDataDto){
        CursDataEntity cursDataEntity = new CursDataEntity(
                        cursDataDto.getCurrencyCode(),
                        cursDataDto.getCurs(),
                        cursDataDto.getCursDate());
        cursDataEntity = cursDataRepository.save(cursDataEntity);
        cursDataDto.setId(cursDataEntity.getId());
        cursDataDto.setCurrencyCode(cursDataEntity.getCurrencyCode().name());
        cursDataDto.setCurs(cursDataEntity.getCurs());
        cursDataDto.setCursDate(cursDataEntity.getCursDate());

        return cursDataDto;
    }

    public CursDataDto update(CursDataDto cursDataDto){
        CursDataEntity cursDataEntity = new CursDataEntity();
        if(cursDataRepository.existsById(cursDataDto.getId())) {
            cursDataEntity.setId(cursDataDto.getId());
            cursDataEntity.setCurrencyCode(cursDataDto.getCurrencyCode().name());
            cursDataEntity.setCurs(cursDataDto.getCurs());
            cursDataEntity.setCursDate(cursDataDto.getCursDate());
            cursDataRepository.save(cursDataEntity);
            cursDataDto.setId(cursDataEntity.getId());
            cursDataDto.setCurrencyCode(cursDataEntity.getCurrencyCode().name());
            cursDataDto.setCurs(cursDataEntity.getCurs());
            cursDataDto.setCursDate(cursDataEntity.getCursDate());
            return cursDataDto;
        }
        return null;
    }

    public List<CursDataDto> select(CursDataDto cursDataDto){
        CursDataEntity cursDataEntity = new CursDataEntity();
        List<CursDataEntity> cursDataEntityList;
        List<CursDataDto> cursDataDtoList = new ArrayList<>();

        cursDataEntity.setCurrencyCode(cursDataDto.getCurrencyCode().name());
        cursDataEntity.setCursDate(cursDataDto.getCursDate());
        cursDataEntityList = cursDataRepository.findByCurrencyCodeAndCursDate(
                                                    cursDataEntity.getCurrencyCode(),
                                                    cursDataEntity.getCursDate());
        for (CursDataEntity entity : cursDataEntityList){
            cursDataDto = new CursDataDto();
            cursDataDto.setId(entity.getId());
            cursDataDto.setCurrencyCode(entity.getCurrencyCode().name());
            cursDataDto.setCurs(entity.getCurs());
            cursDataDto.setCursDate(entity.getCursDate());
            cursDataDtoList.add(cursDataDto);
        }
        return cursDataDtoList;
    }

    public void delete(CursDataDto cursDataDto){
        CursDataEntity cursDataEntity = new CursDataEntity();
        if(cursDataRepository.existsById(cursDataDto.getId())) {
            cursDataEntity = cursDataRepository.getOne(cursDataDto.getId());
            cursDataRepository.delete(cursDataEntity);
        }
    }
}