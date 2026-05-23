package bibliotecaupt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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

    public Livro registarLivro(String titulo, String autor, String isbn, int totalExemplares) {
        sPermissao(utilizadorAutenticado instanceof Bibliotecario, "Apenas bibliotecários ou gestores podem registar livros.");
        sValidar(titulo, "título");
        sValidar(autor, "autor");
        sValidar(isbn, "ISBN");

        if (totalExemplares <= 0) {
            throw new IllegalArgumentException("O número de exemplares tem de ser superior a zero.");
        }

        sISBN(isbn);

        Livro livro = new Livro(titulo.trim(), autor.trim(), isbn.trim(), totalExemplares);
        livros.add(livro);
        return livro;
    }

    public List<Livro> pesquisarLivros(String criterio) {
        exigirAutenticado();
        List<Livro> resultados = new ArrayList<>();
        if (criterio == null || criterio.trim().isEmpty()) {
            return resultados;
        }
        for (Livro l : livros) {
            if (l.correspondePesquisa(criterio.trim())) {
                resultados.add(l);
            }
        }
        return resultados;
    }

    public Reserva requisitarLivro(Aluno aluno, Livro livro) {
        sPermissao(utilizadorAutenticado == aluno, "Só o próprio aluno pode requisitar livros.");
        atualizarRequisicoesEmAtraso();

        if (!aluno.isInscricaoAtiva()) {
            throw new IllegalArgumentException("Apenas alunos com inscrição ativa podem requisitar livros.");
        }

        int ativas = 0;
        for (Reserva r : reservas) {
            if (r.getAluno() == aluno
                    && (r.getEstado() == EstadoReserva.ATIVA || r.getEstado() == EstadoReserva.EM_ATRASO)) {
                ativas++;
                if (r.getEstado() == EstadoReserva.EM_ATRASO) {
                    throw new IllegalArgumentException("Aluno tem requisições em atraso e não pode requisitar novos livros.");
                }
            }
        }
        if (ativas >= MAX_REQUISICOES_ATIVAS) {
            throw new IllegalArgumentException("Aluno já atingiu o máximo de " + MAX_REQUISICOES_ATIVAS + " requisições ativas.");
        }
        if (!livro.estaDisponivel()) {
            throw new IllegalArgumentException("Não existem exemplares disponíveis deste livro.");
        }

        livro.diminuirExemplarDisponivel();
        Reserva nova = new Reserva(proximoIdReserva++, aluno, livro, LocalDate.now());
        reservas.add(nova);
        return nova;
    }

    public List<Reserva> listarRequisicoesAluno(Aluno aluno) {
        sPermissao(utilizadorAutenticado == aluno, "Só o próprio aluno pode consultar as suas requisições.");
        atualizarRequisicoesEmAtraso();

        List<Reserva> ativas = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getAluno() == aluno
                    && (r.getEstado() == EstadoReserva.ATIVA || r.getEstado() == EstadoReserva.EM_ATRASO)) {
                ativas.add(r);
            }
        }
        ativas.sort(Comparator.comparing(Reserva::getDataLimiteDevolucao));
        return ativas;
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

    private void sISBN(String isbn) {
        for (Livro l : livros) {
            if (l.getIsbn().equalsIgnoreCase(isbn.trim())) {
                throw new IllegalArgumentException("Já existe um livro com o ISBN '" + isbn.trim() + "'.");
            }
        }
    }

    private void database() {
        utilizadores.add(new Gestor("Administrador", "admin@upt.pt", "123456"));
        utilizadores.add(new Bibliotecario("Paula Morais", "pmorais@upt.pt", "123456"));
        utilizadores.add(new Aluno("João Dutra", "jdutra@upt.pt", "123456", "52876", true));
        utilizadores.add(new Aluno("Júlia Fernandes", "jfernandes@upt.pt", "123456", "53340", true));

        livros.add(new Livro("Harry Potter e a Pedra Filosofal", "J. K. Rowling", "9789720047335", 3));
        livros.add(new Livro("Os Lusíadas", "Luís de Camões", "9789722334877", 2));
        try {
            Livro indisponivel = livros.get(livros.size() - 1);
            indisponivel.diminuirExemplarDisponivel();
        } catch (IllegalStateException ignored) {
        }
    }
}
