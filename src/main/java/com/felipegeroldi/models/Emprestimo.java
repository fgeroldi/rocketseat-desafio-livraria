package com.felipegeroldi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Emprestimo {
    private Cliente cliente;
    private Livro livro;
    private LocalDateTime dataEmprestimo;
    private Optional<LocalDateTime> dataDevolucao;
}
