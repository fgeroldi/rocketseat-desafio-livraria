package com.felipegeroldi.services;

import com.felipegeroldi.models.Biblioteca;
import com.felipegeroldi.models.Cliente;
import com.felipegeroldi.models.Emprestimo;
import com.felipegeroldi.models.Livro;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

@AllArgsConstructor
public class EmprestimoService {
    private Biblioteca biblioteca;

    public void emprestarLivro() {
        String input;
        var scanner = new Scanner(System.in);

        System.out.println("Digite o titulo do livro: ");
        input = scanner.nextLine();
        Optional<Livro> livro = biblioteca.buscarLivroPorTitulo(input);
        if (livro.isEmpty()) {
            System.out.println("Livro não encontrado");
            return;
        }

        System.out.println("Digite o nome do cliente: ");
        input = scanner.nextLine();
        Optional<Cliente> cliente = biblioteca.buscarClientePorNome(input);
        if (cliente.isEmpty()) {
            System.out.println("Cliente não encontrado");
            return;
        }

        livro.get().setDisponivel(false);
        var emprestimo = Emprestimo.builder()
                .cliente(cliente.get())
                .livro(livro.get())
                .dataEmprestimo(LocalDateTime.now())
                .dataDevolucao(Optional.empty())
                .build();

        cliente.get().adicionarEmprestimo(emprestimo);
        biblioteca.adicionarEmprestimo(emprestimo);
    }

    public void devolverLivro() {
        String input;
        var scanner = new Scanner(System.in);

        System.out.println("Digite o titulo do livro: ");
        input = scanner.nextLine();
        Optional<Livro> livro = biblioteca.buscarLivroPorTitulo(input);
        if (livro.isEmpty()) {
            System.out.println("Livro não encontrado");
            return;
        }

        Optional<Emprestimo> emprestimo = biblioteca.buscarEmprestimoAtivoPorLivro(livro.get());
        emprestimo.ifPresent(e -> {
            e.getLivro().setDisponivel(true);
            e.setDataDevolucao(Optional.of(LocalDateTime.now()));
            System.out.println(String.format(
                    "Livro %s devolvido por %s", e.getLivro().getTitulo(), e.getCliente().getNome()
            ));
        });
    }
}
