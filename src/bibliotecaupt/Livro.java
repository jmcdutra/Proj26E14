package bibliotecaupt;

public class Livro {
    private final String titulo;
    private final String autor;
    private final String isbn;
    private final int totalExemplares;
    private int exemplaresDisponiveis;

    public Livro(String titulo, String autor, String isbn, int totalExemplares) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.totalExemplares = totalExemplares;
        this.exemplaresDisponiveis = totalExemplares;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getTotalExemplares() {
        return totalExemplares;
    }

    public int getExemplaresDisponiveis() {
        return exemplaresDisponiveis;
    }

    public boolean estaDisponivel() {
        return exemplaresDisponiveis > 0;
    }

    public void diminuirExemplarDisponivel() {
        if (exemplaresDisponiveis <= 0) {
            throw new IllegalStateException("Não existem exemplares disponíveis para diminuir.");
        }
        exemplaresDisponiveis--;
    }

    public void aumentarExemplarDisponivel() {
        if (exemplaresDisponiveis >= totalExemplares) {
            throw new IllegalStateException("Já estão disponíveis todos os exemplares.");
        }
        exemplaresDisponiveis++;
    }

    public boolean correspondePesquisa(String criterio) {
        String c = criterio.toLowerCase();
        return titulo.toLowerCase().contains(c)
                || autor.toLowerCase().contains(c)
                || isbn.toLowerCase().contains(c);
    }

    @Override
    public String toString() {
        String estado = estaDisponivel() ? "Disponível" : "Indisponível";
        return "Título: " + titulo
                + " | Autor: " + autor
                + " | ISBN: " + isbn
                + " | Exemplares: " + exemplaresDisponiveis + "/" + totalExemplares
                + " | " + estado;
    }
}
