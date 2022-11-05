import java.util.Stack;

public class ABB {
    //Atributos (precisa de mais??)
    private int altura;
    private ABB subarvoreEsquerda;
    private ABB subarvoreDireita;
    private int valor;

    //Métodos default (feitos por Esther):
    public ABB(){
        altura = -1;
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
    public ABB(Integer... valores){
        altura = 0;
        subarvoreEsquerda = null;
        subarvoreDireita = null;
        valor = valores[0];
        for(int i : valores) {
            if (i != valor) insert(i);
        }
    }
    private int numeroDescedentes(ABB no){
        int nDesc = 0;
        if(subarvoreEsquerda != null) nDesc += subarvoreEsquerda.numeroDescedentes(subarvoreEsquerda) + 1;
        if(subarvoreDireita != null) nDesc += subarvoreDireita.numeroDescedentes(subarvoreDireita) + 1;
        return nDesc;
    }
    public void insert(int x){
        if(altura == -1 && subarvoreDireita == null && subarvoreEsquerda == null){ //negative height = empty tree
            this.valor = x;
            altura = 0;
            return;
        } else if (altura == -1 && subarvoreEsquerda != null) {
            subarvoreEsquerda.insert(x);
        } else if (altura == -1 && subarvoreDireita != null) {
            subarvoreDireita.insert(x);
        }
        if(x<valor){
            if(subarvoreEsquerda == null){
                subarvoreEsquerda = new ABB(x);
                subarvoreEsquerda.altura = this.altura+1;
                System.out.println(x + " adicionado");
            }
            else subarvoreEsquerda.insert(x);
        }
        else if(x>valor){
            if(subarvoreDireita == null){
                subarvoreDireita = new ABB(x);
                subarvoreDireita.altura = this.altura+1;
                System.out.println(x + " adicionado");
            }
            else subarvoreDireita.insert(x);
        } else System.out.println(x + " já está na árvore, não pode ser inserido");
    }
    private void updateHeight(int i){
        altura = i;
        if(subarvoreEsquerda != null) subarvoreEsquerda.updateHeight(i+1);
        if(subarvoreDireita != null) subarvoreDireita.updateHeight(i+1);
    }
    private int menorDaSubarvore(ABB node){
        if(node.subarvoreEsquerda == null){
            return node.valor;
        } else return menorDaSubarvore(node.subarvoreEsquerda);
    }
    public void remove(int x){
        if(altura == -1 && subarvoreDireita == null && subarvoreEsquerda == null){ //negative height = empty tree
            System.out.println(x + " não está na árvore, não pode ser removido");
            return;
        } else if (altura == -1 && subarvoreEsquerda != null) {
            subarvoreEsquerda.remove(x);
        } else if (altura == -1 && subarvoreDireita != null) {
            subarvoreDireita.remove(x);
        }
        if(x<valor){
            if(subarvoreEsquerda != null){
                if(subarvoreEsquerda.valor == x) {
                    if(subarvoreEsquerda.subarvoreEsquerda == null && subarvoreEsquerda.subarvoreDireita == null) subarvoreEsquerda = null;
                    else if(subarvoreEsquerda.subarvoreEsquerda == null && subarvoreEsquerda.subarvoreDireita != null){
                        subarvoreEsquerda.subarvoreDireita.updateHeight(subarvoreEsquerda.altura);
                        subarvoreEsquerda = subarvoreEsquerda.subarvoreDireita;

                    }
                    else if(subarvoreEsquerda.subarvoreEsquerda != null && subarvoreEsquerda.subarvoreDireita == null){
                        subarvoreEsquerda.subarvoreEsquerda.updateHeight(subarvoreEsquerda.altura);
                        subarvoreEsquerda = subarvoreEsquerda.subarvoreEsquerda;
                    }
                    else{
                        int min = menorDaSubarvore(subarvoreEsquerda.subarvoreDireita);
                        subarvoreEsquerda.valor = min;
                        subarvoreEsquerda.subarvoreDireita.remove(min);
                    }
                }
                else subarvoreEsquerda.remove(x);
            }
            else System.out.println(x + " não está na árvore, não pode ser removido");
        }
        else if(x>valor){
            if(subarvoreDireita != null){
                if(subarvoreDireita.valor == x){
                    if(subarvoreDireita.subarvoreEsquerda == null && subarvoreDireita.subarvoreDireita == null) subarvoreDireita = null;
                    else if(subarvoreDireita.subarvoreEsquerda == null && subarvoreDireita.subarvoreDireita != null){
                        subarvoreDireita.subarvoreDireita.updateHeight(subarvoreDireita.altura);
                        subarvoreDireita = subarvoreDireita.subarvoreDireita;
                    }
                    else if(subarvoreDireita.subarvoreEsquerda != null && subarvoreDireita.subarvoreDireita == null){
                        subarvoreDireita.subarvoreEsquerda.updateHeight(subarvoreDireita.altura);
                        subarvoreDireita = subarvoreDireita.subarvoreEsquerda;
                    }
                    else{
                        int min = menorDaSubarvore(subarvoreDireita.subarvoreDireita);
                        subarvoreDireita.valor = min;
                        subarvoreDireita.subarvoreDireita.remove(min);
                    }
                }
                else subarvoreDireita.remove(x);
            }
            else System.out.println(x + " não está na árvore, não pode ser removido");
        } else {
            //delete root
            valor = 0;
            if(subarvoreEsquerda == null && subarvoreDireita == null) altura = -1;
            else if(subarvoreEsquerda == null && subarvoreDireita != null){
                subarvoreDireita.updateHeight(0);
                altura = -1;
            }
            else if(subarvoreEsquerda != null && subarvoreDireita == null){
                subarvoreEsquerda.updateHeight(0);
                altura = -1;
            }
            else{
                int min = menorDaSubarvore(subarvoreDireita);
                valor = min;
                subarvoreDireita.remove(min);
            }
        }
    }

    public void buscarNo(int x, ABB node) {
        if (x < valor) {
            if (subarvoreEsquerda != null) {
                subarvoreEsquerda.buscarNo(x, node);
            }
        } else if (x > valor) {
            if (subarvoreDireita != null) {
                subarvoreDireita.buscarNo(x, node);
            }
        } else {
            node.valor = this.valor;
            node.subarvoreEsquerda = this.subarvoreEsquerda;
            node.subarvoreDireita = this.subarvoreDireita;
        }
    }

    public ABB retornarNo(int x) {
        ABB node = new ABB();
        node.valor = -1;
        buscarNo(x, node);
        return node.valor != -1 ? node : null;
    }

    public boolean buscar(int x) {
        if (retornarNo(x) != null) {
            System.out.println("Chave encontrada");
            return true;
        } else {
            System.out.println("Chave não encontrada");
        }
        return false;
    }

    public void encontrarEnesimoElemento(int n, Integer[] count, Integer[] elementoN, Boolean[] encontrou, ABB node) {
        if (node.subarvoreEsquerda != null && !encontrou[0]) {
            encontrarEnesimoElemento(n, count, elementoN, encontrou, node.subarvoreEsquerda);
        }
        count[0]++;
        if (count[0] == n) {
            encontrou[0] = true;
            elementoN[0] = node.valor;
        }
        if (node.subarvoreDireita != null && !encontrou[0]) {
            encontrarEnesimoElemento(n, count, elementoN, encontrou, node.subarvoreDireita);
        }
    }

    // Métodos de Pedro:
    public int enesimoElemento(int n){
        Integer[] count = {0}, elementoN = {0};
        Boolean[] encontrou = {false};
        encontrarEnesimoElemento(n, count, elementoN, encontrou, this);
        System.out.println(elementoN[0]);
        return elementoN[0];
    }
    public int posicao(int x){
        Integer[] count = {0};
        Boolean[] encontrou = {false};
        encontrarPosicao(x, count, encontrou, this);
        System.out.println(count[0]);
        return count[0];
    }

    private void encontrarPosicao(int x, Integer[] count, Boolean[] encontrou, ABB abb) {
        if (abb.subarvoreEsquerda != null && !encontrou[0]) {
            encontrarPosicao(x, count, encontrou, abb.subarvoreEsquerda);
        }
        if (!encontrou[0])
            count[0]++;
        if (abb.valor == x) {
            encontrou[0] = true;
        }
        if (abb.subarvoreDireita != null && !encontrou[0]) {
            encontrarPosicao(x, count, encontrou, abb.subarvoreDireita);
        }
    }

    public void mediana(){
        System.out.println(enesimoElemento((numeroDescedentes(this)+1)/2));
    }

    public void sum(Double[] sum, Double[] count, ABB node) {
        sum[0] += node.valor;
        count[0]++;
        if (node.subarvoreEsquerda != null)
            sum(sum, count, node.subarvoreEsquerda);
        if (node.subarvoreDireita != null)
            sum(sum, count, node.subarvoreDireita);
    }
    public double media(int x) {
        Double[] sum = {0.0}, count = {0.0};
        ABB abbEncontrada = retornarNo(x);
        sum(sum, count, abbEncontrada);
        System.out.println(sum[0] / count[0]);
        return 0;
    }

    /*public void preOrdem(ABB no){
        System.out.println(no.valor);
        if(no.subarvoreEsquerda != null){
            preOrdem(no.subarvoreEsquerda);
        }
        if(no.subarvoreDireita != null){
            preOrdem(no.subarvoreDireita);
        }
    }*/

    public int maiorAltura(ABB no){
        int altEsq = no.altura, altEsq2 = 0;
        int altDir = no.altura, altDir2 = 0;
        if(no.subarvoreEsquerda != null) altEsq2 = no.maiorAltura(no.subarvoreEsquerda);
        if(no.subarvoreDireita != null) altDir2 = no.maiorAltura(no.subarvoreDireita);
        if(altEsq < altEsq2) altEsq = altEsq2;
        if(altDir < altDir2) altDir = altDir2;
        //if(no.subarvoreEsquerda == null && no.subarvoreDireita == null) return no.altura;
        if(altEsq > altDir){
            return altEsq;
        }
        else return altDir;
    }

    //Métodos de Thuanny:
    public boolean ehCheia(){
        if(this.numeroDescedentes(this) + 1 == Math.pow(2, this.maiorAltura(this) + 1) - 1){
            System.out.println("A árvore é cheia");
            return true;
        }
        System.out.println("A árvore não é cheia");
        return false;
    }
    private boolean ehCompleta(int height){
        if(this.subarvoreEsquerda != null){
            return this.subarvoreEsquerda.ehCompleta(height);
        }
        if(this.subarvoreDireita != null){
            return this.subarvoreDireita.ehCompleta(height);
        }
        if(this.altura < height-1) return false;
        return true;
    }
    public boolean ehCompleta(){
        int height = maiorAltura(this);
        if(this.subarvoreEsquerda != null){
            return this.subarvoreEsquerda.ehCompleta(height);
        }
        if(this.subarvoreDireita != null){
            return this.subarvoreDireita.ehCompleta(height);
        }
        if(this.altura < height) {
            System.out.println("A árvore não é completa");
            return false;
        }
        System.out.println("A árvore é completa");
        return true;
    }
    //Métodos de Esther:
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
        } else if (s==2) {
            System.out.println(stringArvore2());
        } else System.out.println("Desculpe, só existem dois tipos de impressão.\nEntre com '1' ou '2'.");
    }
    private void imprimeArvore1(){
        if(altura != -1) {
            for (int i = 0; i < this.altura * 5; ++i) System.out.print(' ');
            System.out.print(valor);
            for (int i = 0; i < 30 - this.altura * 5; ++i) System.out.print('-');
            System.out.println();
        }
        if(subarvoreEsquerda != null){
            subarvoreEsquerda.imprimeArvore1();
        }
        if(subarvoreDireita != null){
            subarvoreDireita.imprimeArvore1();
        }
    }
    private String stringArvore2(){
        if(altura != -1){
            String fullString = " (";

            fullString += valor;
            if(subarvoreEsquerda != null){
                fullString += subarvoreEsquerda.stringArvore2();
            }
            if(subarvoreDireita != null){
                fullString += subarvoreDireita.stringArvore2();
            }
            fullString += ")";
            return fullString;
        } else if(subarvoreEsquerda == null && subarvoreDireita != null){
            return subarvoreDireita.stringArvore2();
        } else if(subarvoreEsquerda != null && subarvoreDireita == null){
            return subarvoreEsquerda.stringArvore2();
        }
        return "";
    }
}
