package classes;

import java.util.ArrayList;
import java.util.List;

import classes.ordens.Ordem;

public class LivroDeOfertas {

    private List<Ordem> ordens;
    private List<Transacao> transacoes;
    private Acao acao;
    private List<AcaoObserver> acaoObservers;

    public LivroDeOfertas(Acao acao) {

        this.ordens = new ArrayList<>();
        this.transacoes = new ArrayList<>();
        this.acaoObservers = new ArrayList<>();
        this.acao = acao;
    }

    public void addOrdem(Ordem ordem) {
        ordens.add(ordem);

    }

    public void atualizarOrdem(Ordem ordem, int quantidade) {

    }

    private void createTransacao() {

    }

    private void addTransacao(){
        
    }

    private void notifyAllObservers() {

    }
    
    public void addObserver(AcaoObserver acaoObserver){
        acaoObservers.add(acaoObserver);
    }
     
    public void removeObserver(AcaoObserver acaoObserver){
        acaoObservers.remove(acaoObserver);
    }

    public Acao getAcao() {
        return acao;
    }
    public void setAcao(Acao acao) {
        this.acao = acao;
    }

}
