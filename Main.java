public class Main {

    public static void main(String[] args) {
        GerenciadorDeRecursos gerenciadorDeRecursos = new GerenciadorDeRecursos(args[0], args[1]);
        gerenciadorDeRecursos.inicializarABB();
        gerenciadorDeRecursos.realizarOperacoes();
    }
}
