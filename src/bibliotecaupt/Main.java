package bibliotecaupt;

public class Main {
    public static void main(String[] args) {
        SistemaBiblioteca sistema = new SistemaBiblioteca();
        MenuTerminal menu = new MenuTerminal(sistema);
        menu.iniciar();
    }
}
