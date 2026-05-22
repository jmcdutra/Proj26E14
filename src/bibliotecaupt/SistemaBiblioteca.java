package bibliotecaupt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaBiblioteca {
    private static final int MAX_REQUISICOES_ATIVAS = 5;
    private static final int DIAS_DEVOLUCAO = 30;

    private final List<Utilizador> utilizadores;
    private final List<Livro> livros;
    private final List<Reserva> reservas;
    private Utilizador utilizadorAutenticado;
    private int proximoIdReserva;

    public SistemaBiblioteca() {
        this.utilizadores = new ArrayList<>();
        this.livros = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.utilizadorAutenticado = null;
        this.proximoIdReserva = 1;
        database();
        atualizarRequisicoesEmAtraso();
    }

    public Utilizador getUtilizadorAutenticado() {
        return utilizadorAutenticado;
    }

    public Utilizador autenticar(String email, String password) {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Credenciais inválidas.");
        }
        for (Utilizador u : utilizadores) {
            if (u.getEmail().equalsIgnoreCase(email) && u.verificarPassword(password)) {
                utilizadorAutenticado = u;
                return u;
            }
        }
        throw new IllegalArgumentException("Credenciais inválidas.");
    }

    public void terminarSessao() {
        utilizadorAutenticado = null;
    }

    public Aluno registarAluno(String nome, String email, String password, String numeroAluno, boolean inscricaoAtiva) {
        sPermissao(utilizadorAutenticado instanceof Bibliotecario, "Apenas bibliotecários ou gestores podem registar alunos.");
        sValidar(nome, "nome");
        sValidar(email, "email");
        sValidar(password, "password");
        sValidar(numeroAluno, "número de aluno");
        sValidarEmail(email);
        sValidarNAluno(numeroAluno);

        Aluno aluno = new Aluno(nome.trim(), email.trim(), password, numeroAluno.trim(), inscricaoAtiva);
        utilizadores.add(aluno);
        return aluno;
    }

    public Bibliotecario registarBibliotecario(String nome, String email, String password) {
        sPermissao(utilizadorAutenticado instanceof Gestor, "Apenas gestores podem registar bibliotecários.");
        sValidar(nome, "nome");
        sValidar(email, "email");
        sValidar(password, "password");
        sValidarEmail(email);

        Bibliotecario b = new Bibliotecario(nome.trim(), email.trim(), password);
        utilizadores.add(b);
        return b;
    }

    public void atualizarRequisicoesEmAtraso() {
        LocalDate hoje = LocalDate.now();
        for (Reserva r : reservas) {
            if (r.getEstado() == EstadoReserva.ATIVA && r.getDataLimiteDevolucao().isBefore(hoje)) {
                r.marcarEmAtraso();
            }
        }
    }

    private void exigirAutenticado() {
        if (utilizadorAutenticado == null) {
            throw new IllegalArgumentException("É necessário estar autenticado.");
        }
    }

    private void sPermissao(boolean condicao, String mensagem) {
        exigirAutenticado();
        if (!condicao) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    private void sValidar(String valor, String nomeCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("O campo '" + nomeCampo + "' é obrigatório.");
        }
    }

    private void sValidarEmail(String email) {
        for (Utilizador u : utilizadores) {
            if (u.getEmail().equalsIgnoreCase(email.trim())) {
                throw new IllegalArgumentException("Já existe um utilizador com o email '" + email.trim() + "'.");
            }
        }
    }

    private void sValidarNAluno(String numeroAluno) {
        for (Utilizador u : utilizadores) {
            if (u instanceof Aluno && ((Aluno) u).getNumeroAluno().equalsIgnoreCase(numeroAluno.trim())) {
                throw new IllegalArgumentException("Já existe um aluno com o número '" + numeroAluno.trim() + "'.");
            }
        }
    }

    private void database() {
        utilizadores.add(new Gestor("Administrador", "admin@upt.pt", "123456"));
        utilizadores.add(new Bibliotecario("Paula Morais", "pmorais@upt.pt", "123456"));
        utilizadores.add(new Aluno("João Dutra", "jdutra@upt.pt", "123456", "52876", true));
        utilizadores.add(new Aluno("Júlia Fernandes", "jfernandes@upt.pt", "123456", "53340", true));
    }
}
