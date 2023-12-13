package classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import classes.ordens.Ordem;
import classes.ordens.OrdemCompra;
import classes.ordens.OrdemConcrets;
import classes.ordens.OrdemVenda;
import classes.ordens.OrderType;

public class LivroDeOfertas {

    private List<OrdemConcrets> ordens;
    private List<Transacao> transacoes;
    private Acao acao;
    private List<AcaoObserver> acaoObservers;

    public LivroDeOfertas(Acao acao) {

        this.ordens = new ArrayList<>();
        this.transacoes = new ArrayList<>();
        this.acaoObservers = new ArrayList<>();
        this.acao = acao;
    }

    public void addOrdem(OrdemConcrets ordem) {
        ordens.add(ordem);

    }

    public void atualizarOrdem(Ordem ordem, int quantidade) {

 
   }
   




    private void verficarOrdens( OrdemConcrets ordencompra, OrdemConcrets ordensvenda) {
        
        int count = 0;
        for (OrdemConcrets ordemCompra : ordencompra) {
            for (OrdemConcrets ordemVenda : ordensvenda) {
                if (ordemCompra != ordemVenda && ordemCompra.getType() == OrderType.COMPRA && ordemVenda.getType() == OrderType.VENDA) {
                    if(TransacaoManager.criarTransacao(ordemCompra, ordemVenda, this)) count ++;
                }

            }
        }
        System.out.println(count + "transacoes foram criadas");   

    }

    public void notifyAllObservers(Ordem ordem) {
        for (AcaoObserver acaoObserver : acaoObservers) {
            acaoObserver.update(ordem.toString());
        }

    }
    
    public void addObserver(AcaoObserver acaoObserver){
        acaoObservers.add(acaoObserver);
    }
     
    public void removeObserver(AcaoObserver acaoObserver){
        acaoObservers.remove(acaoObserver);
    }

    public void addTransacao(Transacao transacao) {
        this.transacoes.add(transacao);
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public Acao getAcao() {
        return acao;
    }

}
