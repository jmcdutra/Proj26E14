package bibliotecaupt;

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
                sistema.terminarSessao();
            }
        }

        System.out.println("Até breve!");
        scanner.close();
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
}
