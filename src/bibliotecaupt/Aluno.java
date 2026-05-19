package bibliotecaupt;

public class Aluno extends Utilizador {
    private final String numeroAluno;
    private boolean inscricaoAtiva;

    public Aluno(String nome, String email, String password, String numeroAluno, boolean inscricaoAtiva) {
        super(nome, email, password);
        this.numeroAluno = numeroAluno;
        this.inscricaoAtiva = inscricaoAtiva;
    }

    public String getNumeroAluno() {
        return numeroAluno;
    }

    public boolean isInscricaoAtiva() {
        return inscricaoAtiva;
    }

    public void setInscricaoAtiva(boolean inscricaoAtiva) {
        this.inscricaoAtiva = inscricaoAtiva;
    }
}
