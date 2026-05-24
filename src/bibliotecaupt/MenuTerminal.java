package bibliotecaupt;

import java.util.List;
import java.util.Scanner;

public class MenuTerminal {
    private final SistemaBiblioteca sistema;
    private final Scanner scanner;

    public MenuTerminal(SistemaBiblioteca sistema) {
        this.sistema = sistema;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("=============================================================");
        System.out.println("  Biblioteca - Universidade Portucalense Infante D. Henrique");
        System.out.println("=============================================================");

        boolean sair = false;
        while (!sair) {
            if (sistema.getUtilizadorAutenticado() == null) {
                sair = mostrarMenuLogin();
            } else {
                Utilizador u = sistema.getUtilizadorAutenticado();
                if (u instanceof Gestor) {
                    mostrarMenuGestor();
                } else if (u instanceof Bibliotecario) {
                    mostrarMenuBibliotecario();
                } else if (u instanceof Aluno) {
                    mostrarMenuAluno((Aluno) u);
                }
            }
        }

        System.out.println("Até breve!");
        scanner.close();
    }

    private void mostrarMenuAluno(Aluno aluno) {
        System.out.println();
        System.out.println("------------------- PAINEL DO ALUNO -------------------");
        System.out.println("Utilizador: " + aluno.getNome() + " (nº " + aluno.getNumeroAluno() + ")");
        System.out.println("1. Pesquisar livros");
        System.out.println("0. Terminar sessão");
        int opcao = lerOpcao("Opção: ");
        switch (opcao) {
            case 1: opcaoPesquisarLivros(); break;
            case 0: sistema.terminarSessao(); System.out.println("Sessão terminada."); break;
            default: System.out.println("Opção inválida.");
        }
    }

    private void mostrarMenuBibliotecario() {
        System.out.println();
        System.out.println("---------------- PAINEL BIBLIOTECÁRIO ----------------");
        System.out.println("Utilizador: " + sistema.getUtilizadorAutenticado().getNome());
        System.out.println("1. Registar aluno");
        System.out.println("2. Registar livro");
        System.out.println("3. Pesquisar livros");
        System.out.println("0. Terminar sessão");
        int opcao = lerOpcao("Opção: ");
        switch (opcao) {
            case 1: opcaoRegistarAluno(); break;
            case 2: opcaoRegistarLivro(); break;
            case 3: opcaoPesquisarLivros(); break;
            case 0: sistema.terminarSessao(); System.out.println("Sessão terminada."); break;
            default: System.out.println("Opção inválida.");
        }
    }

    private void mostrarMenuGestor() {
        System.out.println();
        System.out.println("------------------- PAINEL GESTOR -------------------");
        System.out.println("Utilizador: " + sistema.getUtilizadorAutenticado().getNome());
        System.out.println("1. Registar bibliotecário");
        System.out.println("2. Registar aluno");
        System.out.println("3. Registar livro");
        System.out.println("4. Pesquisar livros");
        System.out.println("0. Terminar sessão");
        int opcao = lerOpcao("Opção: ");
        switch (opcao) {
            case 1: opcaoRegistarBibliotecario(); break;
            case 2: opcaoRegistarAluno(); break;
            case 3: opcaoRegistarLivro(); break;
            case 4: opcaoPesquisarLivros(); break;
            case 0: sistema.terminarSessao(); System.out.println("Sessão terminada."); break;
            default: System.out.println("Opção inválida.");
        }
    }

    private void opcaoRegistarAluno() {
        try {
            String nome = lerTexto("Nome: ");
            String email = lerTexto("Email: ");
            String password = lerTexto("Password: ");
            String numero = lerTexto("Número de aluno: ");
            boolean ativa = escolha("Inscrição ativa? (s/n): ");
            sistema.registarAluno(nome, email, password, numero, ativa);
            System.out.println("Aluno registado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void opcaoRegistarBibliotecario() {
        try {
            String nome = lerTexto("Nome: ");
            String email = lerTexto("Email: ");
            String password = lerTexto("Password: ");
            sistema.registarBibliotecario(nome, email, password);
            System.out.println("Bibliotecário registado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void opcaoRegistarLivro() {
        try {
            String titulo = lerTexto("Título: ");
            String autor = lerTexto("Autor: ");
            String isbn = lerTexto("ISBN: ");
            int exemplares = lerOpcao("Número de exemplares: ");
            sistema.registarLivro(titulo, autor, isbn, exemplares);
            System.out.println("Livro registado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void opcaoPesquisarLivros() {
        try {
            String criterio = lerTexto("Termo de pesquisa (título, autor ou ISBN): ");
            List<Livro> resultados = sistema.pesquisarLivros(criterio);
            if (resultados.isEmpty()) {
                System.out.println("Nenhum livro encontrado.");
                return;
            }
            System.out.println("Resultados encontrados (" + resultados.size() + "):");
            for (int i = 0; i < resultados.size(); i++) {
                System.out.println("  [" + (i + 1) + "] " + resultados.get(i));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private boolean mostrarMenuLogin() {
        System.out.println();
        System.out.println("------------------- LOGIN -------------------");
        System.out.println("1. Iniciar sessão");
        System.out.println("0. Sair");
        int opcao = lerOpcao("Opção: ");
        switch (opcao) {
            case 1:
                logIn();
                return false;
            case 0:
                return true;
            default:
                System.out.println("Opção inválida.");
                return false;
        }
    }

    private void logIn() {
        try {
            String email = lerTexto("Email: ");
            String password = lerTexto("Password: ");
            Utilizador u = sistema.autenticar(email, password);
            System.out.println("Seja bem-vindo(a), " + u.getNome() + ".");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private int lerOpcao(String prompt) {
        while (true) {
            System.out.print(prompt);
            String linha = scanner.nextLine();
            try {
                return Integer.parseInt(linha.trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido, introduza um número inteiro.");
            }
        }
    }

    private String lerTexto(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private boolean escolha(String prompt) {
        while (true) {
            System.out.print(prompt);
            String linha = scanner.nextLine().trim().toLowerCase();
            if (linha.equals("s") || linha.equals("sim")) return true;
            if (linha.equals("n") || linha.equals("nao") || linha.equals("não")) return false;
            System.out.println("Resposta inválida, por favor responda 's' ou 'n'!");
        }
    }
}
