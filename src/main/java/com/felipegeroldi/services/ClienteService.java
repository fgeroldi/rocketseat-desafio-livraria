package com.felipegeroldi.services;

import com.felipegeroldi.models.Biblioteca;
import com.felipegeroldi.models.Cliente;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

@AllArgsConstructor
public class ClienteService {
    private Biblioteca biblioteca;

    public void cadastrarCliente() {
        String input;
        var scanner = new Scanner(System.in);

        System.out.println("Nome do cliente: ");
        input = scanner.nextLine();
        Optional<Cliente> cliente = biblioteca.buscarClientePorNome(input);
        if (cliente.isPresent()) {
            System.out.println("Cliente já cadastrado: " + cliente.get().getNome());
            return;
        }
        String nome = input;

        System.out.println("Email do cliente: ");
        String email = scanner.nextLine();

        System.out.println("Data de nascimento do cliente(dd/MM/yyyy): ");
        input = scanner.nextLine();

        LocalDate dataNascimento;
        try {
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNascimento = LocalDate.parse(input, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Data de nascimento inválida");
            return;
        }

        var novoCliente = Cliente.builder()
                .nome(nome)
                .email(email)
                .dataNascimento(dataNascimento)
                .emprestimos(new ArrayList<>())
                .build();

        biblioteca.adicionarCliente(novoCliente);
    }
}
