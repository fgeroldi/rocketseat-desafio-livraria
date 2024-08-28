package com.felipegeroldi.services;

import com.felipegeroldi.models.Autor;
import com.felipegeroldi.models.Biblioteca;
import com.felipegeroldi.models.Livro;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;

@AllArgsConstructor
public class BibliotecaService {
    private Biblioteca biblioteca;

    public void adicionarLivro() {
        String input;
        var scanner = new Scanner(System.in);

        System.out.printf("Titulo do livro: ");
        input = scanner.nextLine();
        Optional<Livro> livro = biblioteca.buscarLivroPorTitulo(input);
        if (livro.isPresent()) {
            System.out.println("Livro já cadastrado: " + livro.get().getTitulo());
            return;
        }
        String titulo = input;

        System.out.println("Nome do autor: ");
        input = scanner.nextLine();
        Optional<Autor> autor = biblioteca.buscarAutorPorNome(input);
        if(autor.isEmpty()) {
            String nomeAutor = input;
            System.out.println("Data de nascimento do autor(dd/MM/yyyy): ");
            input = scanner.nextLine();

            try {
                var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                var dataNascimento = LocalDate.parse(input, formatter);

                autor = Optional.of(Autor.builder()
                        .nome(nomeAutor)
                        .dataNascimento(dataNascimento)
                        .build());

                biblioteca.adicionarAutor(autor.get());
            } catch (DateTimeParseException e) {
                System.out.println("Data de nascimento inválida!");
                return;
            }
        }

        var novoLivro = Livro.builder()
                .titulo(input)
                .autor(autor.get())
                .dataCadastro(LocalDateTime.now())
                .dataAtualizacao(LocalDateTime.now())
                .disponivel(true)
                .build();

        biblioteca.adicionarLivro(novoLivro);
        System.out.println("Livro adicionado");
    }
}
