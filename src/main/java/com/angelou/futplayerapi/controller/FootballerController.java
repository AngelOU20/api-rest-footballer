package com.angelou.futplayerapi.controller;

import com.angelou.futplayerapi.model.dto.FootballerDto;
import com.angelou.futplayerapi.model.entity.Footballer;
import com.angelou.futplayerapi.model.payload.MessageResponse;
import com.angelou.futplayerapi.service.IFootballerService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class FootballerController {

    private final IFootballerService footballerService;

    public FootballerController(IFootballerService footballerService) {
        this.footballerService = footballerService;
    }

    @PostMapping("footballer")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody FootballerDto footballerDto) {
        Footballer footballerSave = null;
        try {
            footballerSave = footballerService.save(footballerDto);
            footballerDto = FootballerDto.builder()
                    .id(footballerSave.getId())
                    .name(footballerSave.getName())
                    .lastname(footballerSave.getLastname())
                    .features(footballerSave.getFeatures())
                    .biography(footballerSave.getBiography())
                    .birthdate(footballerSave.getBirthdate())
                    .deathdate(footballerSave.getDeathdate())
                    .position(footballerSave.getPosition())
                    .build();

            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Saved successfully!")
                    .object(footballerDto)
                    .build(),
                    HttpStatus.CREATED);

        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message(exDt.getMessage())
                    .object(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

/*
    @PutMapping("footballer")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody FootballerDto footballerDto) {
//        Footballer footballerUpdated = footballerService.save(footballerDto);
//        return FootballerDto.builder()
//                .id(footballerUpdated.getId())
//                .name(footballerUpdated.getName())
//                .lastname(footballerUpdated.getLastname())
//                .features(footballerUpdated.getFeatures())
//                .biography(footballerUpdated.getBiography())
//                .birthdate(footballerUpdated.getBirthdate())
//                .deathdate(footballerUpdated.getDeathdate())
//                .position(footballerUpdated.getPosition())
//                .build();
        Footballer footballerUpdated = null;
        try {
            footballerUpdated = footballerService.save(footballerDto);
            footballerDto = FootballerDto.builder()
                    .id(footballerUpdated.getId())
                    .name(footballerUpdated.getName())
                    .lastname(footballerUpdated.getLastname())
                    .features(footballerUpdated.getFeatures())
                    .biography(footballerUpdated.getBiography())
                    .birthdate(footballerUpdated.getBirthdate())
                    .deathdate(footballerUpdated.getDeathdate())
                    .position(footballerUpdated.getPosition())
                    .build();

            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Successfully updated!")
                    .object(footballerDto)
                    .build(),
                    HttpStatus.OK);

        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message(exDt.getMessage())
                    .object(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
*/

    @PutMapping("footballer/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody FootballerDto footballerDto) {
        Footballer footballerUpdated = null;
        try {
//            Footballer footballerFound = footballerService.findById(id);
//            footballerFound == null
            if (!(footballerService.existsById(id))) {
                return new ResponseEntity<>(MessageResponse.builder()
                        .message("El futbolista no existe!")
                        .object(null)
                        .build(),
                        HttpStatus.NOT_FOUND);
            }

            footballerDto.setId(id);
            footballerUpdated = footballerService.save(footballerDto);
            footballerDto = FootballerDto.builder()
                    .id(footballerUpdated.getId())
                    .name(footballerUpdated.getName())
                    .lastname(footballerUpdated.getLastname())
                    .features(footballerUpdated.getFeatures())
                    .biography(footballerUpdated.getBiography())
                    .birthdate(footballerUpdated.getBirthdate())
                    .deathdate(footballerUpdated.getDeathdate())
                    .position(footballerUpdated.getPosition())
                    .build();

            return new ResponseEntity<>(MessageResponse.builder()
                    .message("Successfully updated!")
                    .object(footballerDto)
                    .build(),
                    HttpStatus.OK);

        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message(exDt.getMessage())
                    .object(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("footballer/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT) // = respuesta estatica
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Footballer footballerDelete = footballerService.findById(id);
            footballerService.deleteFootballer(footballerDelete);
            return new ResponseEntity<>(footballerDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message(exDt.getMessage())
                    .object(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("footballer/{id}")
//    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Footballer footballer = footballerService.findById(id);

        if (footballer == null) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("El registro que intenta buscar, no existe!")
                    .object(null)
                    .build(),
                    HttpStatus.NOT_FOUND);
        }

//        return FootballerDto.builder()
//                .id(footballer.getId())
//                .name(footballer.getName())
//                .lastname(footballer.getLastname())
//                .features(footballer.getFeatures())
//                .biography(footballer.getBiography())
//                .birthdate(footballer.getBirthdate())
//                .deathdate(footballer.getDeathdate())
//                .position(footballer.getPosition())
//                .build();

        return new ResponseEntity<>(MessageResponse.builder()
                .message("Successfully search!")
                .object(FootballerDto.builder()
                        .id(footballer.getId())
                        .name(footballer.getName())
                        .lastname(footballer.getLastname())
                        .features(footballer.getFeatures())
                        .biography(footballer.getBiography())
                        .birthdate(footballer.getBirthdate())
                        .deathdate(footballer.getDeathdate())
                        .position(footballer.getPosition())
                        .build())
                .build(),
                HttpStatus.OK);
    }

    @GetMapping("footballers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAll() {
        try {
            List<Footballer> footballers = footballerService.findAll();
            List<FootballerDto> footballerDtos = footballers.stream()
                    .map(footballer -> FootballerDto.builder()
                            .id(footballer.getId())
                            .name(footballer.getName())
                            .lastname(footballer.getLastname())
                            .features(footballer.getFeatures())
                            .biography(footballer.getBiography())
                            .birthdate(footballer.getBirthdate())
                            .deathdate(footballer.getDeathdate())
                            .position(footballer.getPosition())
                            .build())
                    .collect(Collectors.toList());
            return new ResponseEntity<>(footballerDtos, HttpStatus.OK);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message(exDt.getMessage())
                    .object(null)
                    .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}
