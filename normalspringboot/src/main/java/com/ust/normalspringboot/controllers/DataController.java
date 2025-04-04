package com.ust.normalspringboot.controllers;
import com.ust.normalspringboot.dto.DataDTO;
import com.ust.normalspringboot.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @PostMapping("/save")
    public ResponseEntity<DataDTO> saveData(@RequestBody DataDTO dataDTO) {
        DataDTO savedDTO = dataService.saveData(dataDTO);
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DataDTO>> getAllData() {
        List<DataDTO> allData = dataService.getAllData();
        return new ResponseEntity<>(allData, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDTO> getDataById(@PathVariable Long id) {
        DataDTO dataDTO = dataService.getDataById(id);
        if (dataDTO != null) {
            return new ResponseEntity<>(dataDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}