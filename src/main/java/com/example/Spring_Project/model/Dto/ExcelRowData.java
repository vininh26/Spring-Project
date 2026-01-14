package com.example.Spring_Project.model.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.function.BiConsumer;

@Getter
@Setter
@NoArgsConstructor
public class ExcelRowData {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String company;
    private String text;
    private String description;
    private String jobTitle;


}
