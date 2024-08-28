package com.felipegeroldi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Autor {
    private int id;
    private String nome;
    private LocalDate dataNascimento;
}
