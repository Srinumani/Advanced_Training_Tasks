package com.ust.normalspringboot.services;

import com.ust.normalspringboot.dto.DataDTO;
import com.ust.normalspringboot.models.DataModel;
import com.ust.normalspringboot.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataRepository dataRepository;

    @Override
    public DataDTO saveData(DataDTO dataDTO) {
        DataModel dataModel = new DataModel(dataDTO.getName(), dataDTO.getDescription());
        DataModel savedModel = dataRepository.save(dataModel);
        return new DataDTO(savedModel.getId(), savedModel.getName(), savedModel.getDescription());
    }

    @Override
    public List<DataDTO> getAllData() {
        return dataRepository.findAll().stream()
                .map(model -> new DataDTO(model.getId(), model.getName(), model.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public DataDTO getDataById(Long id) {
        return dataRepository.findById(id)
                .map(model -> new DataDTO(model.getId(), model.getName(), model.getDescription()))
                .orElse(null);
    }
}