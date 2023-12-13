package classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.stream.Collectors;

import classes.ordens.Ordem;
import classes.ordens.OrdemCompra;
import classes.ordens.OrdemConcrets;
import classes.ordens.OrdemInfo;
import classes.ordens.OrdemVenda;
import classes.ordens.OrderType;

public class   LivroDeOfertas {

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
        notifyAllObservers(ordem);
    }

    public String pesquisarOperacaoInfo(OrdemInfo ordemInfo){

        String result = "";

        List<OrdemConcrets> ordemsResuList = new ArrayList<>();
        int horarioOrdemInfo = ordemInfo.getDateTime().getHour();
        int diaOrdemInfo = ordemInfo.getDateTime().getDayOfMonth();
        int mesOrdemInfo = ordemInfo.getDateTime().getMonthValue();
        int anoOrdemInfo= ordemInfo.getDateTime().getYear();

        return ordens.stream()
            .filter(ordem ->
                    ordem.getData().getHour() == horarioOrdemInfo &&
                    ordem.getData().getDayOfMonth() == diaOrdemInfo &&
                    ordem.getData().getMonthValue() == mesOrdemInfo &&
                    ordem.getData().getYear() == anoOrdemInfo)
            .map(Ordem::toString)
            .collect(Collectors.joining("\n"));
    }

    



    public int verficarOrdens() {
        int count = 0;
        for (OrdemConcrets ordemCompra : ordens) {
            for (OrdemConcrets ordemVenda : ordens) {
                if (ordemCompra != ordemVenda && ordemCompra.getType() == OrderType.COMPRA && ordemVenda.getType() == OrderType.VENDA && ordemVenda.getAtivo() == true && ordemCompra.getAtivo() == true) {
                    Transacao transacao = TransacaoManager.criarTransacao(ordemCompra, ordemVenda, this);
                    if(transacao != null) count ++;
                        System.out.println(transacao.toString());
                }

            }
        }
        System.out.println(count + "transacoes foram criadas");  
        return count; 
    }

    public void notifyAllObservers(OrdemConcrets ordem) {
        for (AcaoObserver acaoObserver : acaoObservers) {
            acaoObserver.update(ordem.getNotificacao());
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

    public boolean temObserver(AcaoObserver observer){
        return acaoObservers.contains(observer);
    }

    public List<OrdemConcrets> getOrdens() {
        return ordens;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }
}
