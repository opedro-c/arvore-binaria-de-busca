import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GerenciadorDeRecursos {
    
    private final String caminhoArvoreInicial;
    private final String caminhoOperacoes;
    ABB abb;  

    public GerenciadorDeRecursos(String caminhoArvoreInicial, String caminhoOperacoes) {
        this.caminhoArvoreInicial = caminhoArvoreInicial;
        this.caminhoOperacoes = caminhoOperacoes;
    }

    private String lerArquivo(String caminhoDoArquivo) throws IOException {
        return Files.readString(Paths.get(caminhoDoArquivo));
    }

    public void inicializarABB() {
        List<Integer> arvoreInicial = new ArrayList<>();
        try {
            for (var num : lerArquivo(caminhoArvoreInicial).split(" "))
                arvoreInicial.add(Integer.parseInt(num));
        } catch (IOException e) {
            System.out.println("Um erro ocorreu ao tentar ler o arquivo da árvore inicial!");
        }
        abb = new ABB(arvoreInicial.toArray(new Integer[0]));
    }

    public void realizarOperacoes() {
        try (Stream<String> stream = Files.lines(Paths.get(caminhoOperacoes))) {
            stream.forEach((input) -> {
                String[] op = input.split(" ");
                switch (op[0]) {
                    case "BUSCAR" -> abb.buscar(Integer.parseInt(op[1]));
                    case "INSIRA" -> abb.insert(Integer.parseInt(op[1]));
                    case "REMOVA" -> abb.remove(Integer.parseInt(op[1]));
                    case "ENESIMO" -> abb.enesimoElemento(Integer.parseInt(op[1]));
                    case "POSICAO" -> abb.posicao(Integer.parseInt(op[1]));
                    case "MEDIANA" -> abb.mediana();
                    case "MEDIA" -> abb.media(Integer.parseInt(op[1]));
                    case "CHEIA" -> abb.ehCheia();
                    case "COMPLETA" -> abb.ehCompleta();
                    case "PREORDEM" -> abb.pre_ordem();
                    case "IMPRIMA" -> abb.imprimeArvore(Integer.parseInt(op[1]));
                    default -> System.out.println("Método não mapeado!");
                }
            });
        } catch (IOException e) {
            System.out.println("Um erro ocorreu ao tentar ler o arquivo de operações!");
        }
    }
}
