package com.example.Spring_Project.service;

import com.example.Spring_Project.entity.CompanyEntity;
import com.example.Spring_Project.model.Dto.CompanyDto;
import com.example.Spring_Project.repository.CompanyRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImportDataService {

    private final CompanyRepository companyRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public int importDataCompanyFromJsonFile() throws Exception {
//        File file = new File("data/data-company.json");
//        List<CompanyDto> dtos = objectMapper.readValue(file, new TypeReference<>() {});
//        List<CompanyEntity> entities = dtos.stream()
//                .map(d -> CompanyEntity.builder()
//                        .name(d.getName())
//                        .phone(d.getPhone())
//                        .address(d.getAddress())
//                        .active(d.isActive() ? d.isActive() : true)
//                        .build())
//                .toList();
//        return entities.size();

        var resource = new ClassPathResource("data/data-company.json");
        try (var is = resource.getInputStream()) {
            var dtos = objectMapper.readValue(is, new TypeReference<java.util.List<CompanyDto>>() {});
            var entities = dtos.stream()
                    .map(d -> CompanyEntity.builder()
                            .name(d.getName())
                           .phone(d.getPhone())
                           .address(d.getAddress())
                           .active(d.isActive() ? d.isActive() : true)
                           .build())
                    .toList();
            companyRepository.saveAll(entities);
            return entities.size();
        }

    }

}
