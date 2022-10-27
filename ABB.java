public class ABB {
    //Atributos (precisa de mais??)
    private int altura;
    private ABB subarvoreEsquerda;
    private ABB subarvoreDireita;
    private int valor;

    //Métodos default:
    public ABB(){
        altura = 0;
        subarvoreEsquerda = null;
        subarvoreDireita = null;
        valor = 0;
    }
    public ABB(int valor){
        altura = 0;
        subarvoreEsquerda = null;
        subarvoreDireita = null;
        this.valor = valor;
    }
    public void insert(int x){
        if(x<valor){
            if(subarvoreEsquerda == null){
                subarvoreEsquerda = new ABB(x);
                subarvoreEsquerda.altura = this.altura+1;
            }
            else subarvoreEsquerda.insert(x);
        }
        else if(x>valor){
            if(subarvoreDireita == null){
                subarvoreDireita = new ABB(x);
                subarvoreDireita.altura = this.altura+1;
            }
            else subarvoreDireita.insert(x);
        } else return;
    }
    public void remove(int x){

    }

    /* Métodos de Pedro:
    * public int enesimoElemento(int n){
        return 0;
    }
    * public int posicao(int x){
        return 0;
    }
    * public  int mediana(){
        return 0;
    }
    * public double media(int x){
        return 0;
    }*/

    //Métodos de Esther:
    public boolean ehCheia(){

        return false;
    }
    public boolean ehCompleta(){
        return false;
    }
    public String pre_ordem(){
        return "Gaiata";
    }
    public void imprimeArvore(int s){
        if(s==1){

        } else if (s==2) {

        } else System.out.println("Desculpe, só existem dois tipos de impressão.\nEntre com '1' ou '2'.");
    }
}
