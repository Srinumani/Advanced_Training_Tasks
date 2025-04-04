package com.ust.normalspringboot.services;

import com.ust.normalspringboot.dto.DataDTO;

import java.util.List;

public interface DataService {
    DataDTO saveData(DataDTO dataDTO);
    List<DataDTO> getAllData();
    DataDTO getDataById(Long id);
}
