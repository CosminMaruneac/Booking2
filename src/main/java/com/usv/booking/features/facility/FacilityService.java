package com.usv.booking.features.facility;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacilityService {

  private final FacilityRepository facilityRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public FacilityService(FacilityRepository facilityRepository,
                         ModelMapper modelMapper) {
    this.facilityRepository = facilityRepository;
    this.modelMapper = modelMapper;
  }

  public List<FacilityDto> bulkCreate(List<FacilityDto> dtos) {

    return dtos.stream().map(
            dto -> {

              Facility facility = facilityRepository.save(modelMapper.map(dto, Facility.class));
              return modelMapper.map(facility, FacilityDto.class);
            })
        .collect(Collectors.toList());

  }

  public void bulkDelete(List<Long> ids) {

    ids.forEach(
        id -> {
          Facility facility = facilityRepository.findById(id).orElseThrow(EntityNotFoundException::new);
          facilityRepository.delete(facility);
        });

  }

  public FacilityDto update(FacilityDto dto) {

    return facilityRepository.findById(dto.getId())
        .map(facility -> {
          facility.setName(dto.getName());
          facilityRepository.save(facility);
          return modelMapper.map(facility, FacilityDto.class);
        })
        .orElseThrow(EntityNotFoundException::new);
  }

  public List<FacilityDto> getAll() {

    return facilityRepository.findAll().stream()
        .map(facility -> modelMapper.map(facility, FacilityDto.class))
        .collect(Collectors.toList());
  }

  public FacilityDto getById(Long id) {

    return facilityRepository.findById(id)
        .map(facility -> modelMapper.map(facility, FacilityDto.class))
        .orElseThrow(EntityNotFoundException::new);
  }
}
