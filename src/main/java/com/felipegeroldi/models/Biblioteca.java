package com.felipegeroldi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Biblioteca {
    private List<Cliente> clientes;
    private List<Livro> livros;
    private List<Autor> autores;
    private List<Emprestimo> emprestimos;

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public Optional<Livro> buscarLivroPorTitulo(String titulo) {
        return livros.stream().filter(l -> l.getTitulo().equalsIgnoreCase(titulo)).findFirst();
    }

    public Optional<Autor> buscarAutorPorNome(String nome) {
        return autores.stream().filter(a -> a.getNome().equalsIgnoreCase(nome)).findFirst();
    }

    public void adicionarAutor(Autor autor) {
        autores.add(autor);
    }

    public Optional<Cliente> buscarClientePorNome(String nome) {
        return clientes.stream().filter(c -> c.getNome().equalsIgnoreCase(nome)).findFirst();
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    public Optional<Emprestimo> buscarEmprestimoAtivoPorLivro(Livro livro) {
        return emprestimos.stream().filter(l -> l.getLivro() == livro && l.getDataDevolucao().isPresent()).findFirst();
    }
}
