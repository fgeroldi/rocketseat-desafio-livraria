package com.felipegeroldi;

import com.felipegeroldi.models.Biblioteca;
import com.felipegeroldi.services.BibliotecaService;
import com.felipegeroldi.services.ClienteService;
import com.felipegeroldi.services.EmprestimoService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean continuar = true;
        System.out.println("Livraria");
        var scanner = new Scanner(System.in);
        String input;
        int opcao;

        var biblioteca = Biblioteca.builder()
                .autores(new ArrayList<>())
                .clientes(new ArrayList<>())
                .livros(new ArrayList<>())
                .emprestimos(new ArrayList<>())
                .build();

        var bibliotecaService = new BibliotecaService(biblioteca);
        var clienteService = new ClienteService(biblioteca);
        var emprestimoService = new EmprestimoService(biblioteca);

        while(continuar)
        {
            System.out.println("Menu");
            System.out.println("1- Adicionar cliente");
            System.out.println("2- Adicionar livro");
            System.out.println("3- Adicionar emprestimo");
            System.out.println("4- Devolver livro");
            System.out.println("5- Sair");
            System.out.println("> ");

            input = scanner.nextLine();
            try {
                opcao = Integer.parseInt(input);

                switch (opcao) {
                    case 1:
                        clienteService.cadastrarCliente();
                        break;
                    case 2:
                        bibliotecaService.adicionarLivro();
                        break;
                    case 3:
                        emprestimoService.emprestarLivro();
                        break;
                    case 4:
                        emprestimoService.devolverLivro();
                        break;
                    case 5:
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida");
            }
        }
    }
}