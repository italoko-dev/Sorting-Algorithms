
package applista;

public class No 
{
    private No prox;
    private No ant;
    private int info;

    public No(No ant, No prox, int info) {
        this.ant = ant;
        this.prox = prox;
        this.info = info;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }

    public No getAnt() {
        return ant;
    }

    public void setAnt(No ant) {
        this.ant = ant;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }
}
