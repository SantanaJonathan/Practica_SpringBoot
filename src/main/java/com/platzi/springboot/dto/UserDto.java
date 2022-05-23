package com.platzi.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserDto {
    //data transfer objet (dto)
    private Long id;
    private String name;
    private LocalDate birthDate;
}
