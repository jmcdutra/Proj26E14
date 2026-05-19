package bibliotecaupt;

public abstract class Utilizador {
    private final String nome;
    private final String email;
    private final String password;

    public Utilizador(String nome, String email, String password) {
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public boolean verificarPassword(String password) {
        return this.password.equals(password);
    }
}
