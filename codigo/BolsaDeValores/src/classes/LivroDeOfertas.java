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
   


    private void createTransacao(Ordem ordem, LocalDateTime datahora, int quantidade, double valor , Acao acao) {
        Transacao transacao = new Transacao(ordem, datahora, quantidade, valor , acao);
    }

    private void verficarOrdens(List<? extends OrdemConcrets> ordencompra, List<? extends OrdemConcrets> ordensvenda) {

        for (OrdemConcrets ordemCompra : ordencompra) {
            for (OrdemConcrets ordemVenda : ordensvenda) {
                if (ordemCompra.getValor() >= ordemVenda.getValor()) {
                    int quantidade = ordemCompra.getQuantidade() - ordemVenda.getQuantidade();
                    if (quantidade > 0) {
                        createTransacao(ordemCompra, LocalDateTime.now(), ordemVenda.getQuantidade(),
                                ordemVenda.getValor(), acao);
                        ordemCompra.atualizar(quantidade);
                        ordensvenda.remove(ordemVenda);
                    } else if (quantidade < 0) {
                        createTransacao(ordemCompra, LocalDateTime.now(), ordemCompra.getQuantidade(),
                                ordemVenda.getValor(), acao);
                        ordemVenda.atualizar(quantidade);
                        ordencompra.remove(ordemCompra);
                    } else {
                        createTransacao(ordemCompra, LocalDateTime.now(), ordemCompra.getQuantidade(),
                                ordemVenda.getValor(), acao);
                        ordencompra.remove(ordemCompra);
                        ordensvenda.remove(ordemVenda);
                    }
                }
            }
        }
        

        
        
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

    public Acao getAcao() {
        return acao;
    }
    public void setAcao(Acao acao) {
        this.acao = acao;
    }

}
