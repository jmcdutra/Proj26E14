package bibliotecaupt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reserva {
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final int id;
    private final Aluno aluno;
    private final Livro livro;
    private final LocalDate dataRequisicao;
    private LocalDate dataLimiteDevolucao;
    private LocalDate dataDevolucaoEfetiva;
    private EstadoReserva estado;
    private boolean renovada;

    public Reserva(int id, Aluno aluno, Livro livro, LocalDate dataRequisicao) {
        this.id = id;
        this.aluno = aluno;
        this.livro = livro;
        this.dataRequisicao = dataRequisicao;
        this.dataLimiteDevolucao = dataRequisicao.plusDays(30);
        this.dataDevolucaoEfetiva = null;
        this.estado = EstadoReserva.ATIVA;
        this.renovada = false;
    }

    public int getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataRequisicao() {
        return dataRequisicao;
    }

    public LocalDate getDataLimiteDevolucao() {
        return dataLimiteDevolucao;
    }

    public LocalDate getDataDevolucaoEfetiva() {
        return dataDevolucaoEfetiva;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public boolean isRenovada() {
        return renovada;
    }

    public boolean estaEmAtraso() {
        return estado == EstadoReserva.EM_ATRASO
                || (estado == EstadoReserva.ATIVA && dataLimiteDevolucao.isBefore(LocalDate.now()));
    }

    public void marcarEmAtraso() {
        if (estado == EstadoReserva.ATIVA) {
            estado = EstadoReserva.EM_ATRASO;
        }
    }

    @Override
    public String toString() {
        String marcador = estaEmAtraso() ? " [EM ATRASO]" : "";
        return "Título: " + livro.getTitulo()
                + " | Autor: " + livro.getAutor()
                + " | Requisitado: " + dataRequisicao.format(FORMATO_DATA)
                + " | Data limite: " + dataLimiteDevolucao.format(FORMATO_DATA)
                + marcador;
    }
}
