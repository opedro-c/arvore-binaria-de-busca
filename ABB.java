import java.util.Stack;

public class ABB {
    //Atributos (precisa de mais??)
    private int altura;
    private ABB subarvoreEsquerda;
    private ABB subarvoreDireita;
    private int valor;
    //private int numTracos; //p/ impressao 1

    //Métodos default:
    public ABB(){
        altura = 0;
        subarvoreEsquerda = null;
        subarvoreDireita = null;
        valor = 0;
        //numTracos = 30;
    }
    public ABB(int valor){
        altura = 0;
        subarvoreEsquerda = null;
        subarvoreDireita = null;
        this.valor = valor;
        //numTracos = 30;
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
        Stack values = new Stack<ABB>();
        String order="";
        values.push(this);
        while(!values.empty()){
            ABB top = (ABB) values.pop();
            System.out.println(top.valor);
            if(top.subarvoreDireita != null){
                values.push(top.subarvoreDireita);
            }
            if(top.subarvoreEsquerda != null){
                values.push(top.subarvoreEsquerda);
            }
        }
        while (!values.empty()) {
            order+=((ABB)(values.peek())).valor;
            order+=" ";
            values.pop();
        }
        return order;
    }
    public void imprimeArvore(int s){
        if(s==1){
            imprimeArvore1();
            //numTracos = 30;
        } else if (s==2) {
            System.out.println(stringArvore2());
        } else System.out.println("Desculpe, só existem dois tipos de impressão.\nEntre com '1' ou '2'.");
    }
    private void imprimeArvore1(){
        for (int i=0; i<this.altura*5; ++i) System.out.print(' ');
        System.out.print(valor);
        for (int i=0; i<30-this.altura*5; ++i) System.out.print('-');
        System.out.println();
        if(subarvoreEsquerda != null){
            subarvoreEsquerda.imprimeArvore1();
        }
        if(subarvoreDireita != null){
            subarvoreDireita.imprimeArvore1();
        }
    }
    private String stringArvore2(){
        String fullString = " (";
        fullString += valor;
        //fullString += " ";
        if(subarvoreEsquerda != null){
            fullString += subarvoreEsquerda.stringArvore2();
        }
        if(subarvoreDireita != null){
            fullString += subarvoreDireita.stringArvore2();
        }
        fullString += ")";
        return fullString;
    }

}
