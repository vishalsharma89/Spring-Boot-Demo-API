package com.learnjava.learn.services;

import com.learnjava.learn.dto.EmployeeDTO;
import com.learnjava.learn.entities.EmployeeEntity;
import com.learnjava.learn.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeService {

    final EmployeeRepository employeeRepository;
    final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeById(Long id){
        EmployeeEntity employeeEntity=employeeRepository.getById(id);
        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO){
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository
                .findAll()
                .stream()
                .map(employeeEntity->modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
