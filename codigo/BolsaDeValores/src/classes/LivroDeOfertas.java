package classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import classes.ordens.Ordem;
import classes.ordens.OrdemCompra;
import classes.ordens.OrdemConcrets;
import classes.ordens.OrdemInfo;
import classes.ordens.OrdemVenda;
import classes.ordens.OrderType;

public class    LivroDeOfertas {

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

    public String pesquisarOperacaoInfo(OrdemInfo ordemInfo){

        String result = "";

        List<Ordem> ordemsResuList = new ArrayList<>();
        int horarioOrdemInfo = ordemInfo.getDateTime().getHour();
        int diaOrdemInfo = ordemInfo.getDateTime().getDayOfMonth();
        int mesOrdemInfo = ordemInfo.getDateTime().getMonthValue();
        int AnoOrdemInfo= ordemInfo.getDateTime().getYear();

        for(OrdemConcrets ordem: ordens){
            int horarioOrdem = ordem.getData().getHour();
            int diaOrdem = ordem.getData().getDayOfMonth();
            int mesOrdem = ordem.getData().getMonthValue();
            int AnoOrdem = ordem.getData().getYear();
            if(diaOrdemInfo == diaOrdem && mesOrdemInfo == mesOrdem && AnoOrdemInfo == AnoOrdem && horarioOrdem == horarioOrdemInfo)
                ordemsResuList.add(ordem);
        }

        for (Ordem ordem : ordemsResuList) {
            result += ordem.toString() + "\n";
        }

        return result;

    }



    public int verficarOrdens() {
        int count = 0;
        for (OrdemConcrets ordemCompra : ordens) {
            for (OrdemConcrets ordemVenda : ordens) {
                if (ordemCompra != ordemVenda && ordemCompra.getType() == OrderType.COMPRA && ordemVenda.getType() == OrderType.VENDA && ordemVenda.getAtivo() == true && ordemCompra.getAtivo() == true) {
                    if(TransacaoManager.criarTransacao(ordemCompra, ordemVenda, this)) count ++;
                }

            }
        }
        System.out.println(count + "transacoes foram criadas");  
        return count; 
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

    public boolean temObserver(AcaoObserver observer){
        return acaoObservers.contains(observer);
    }

    public List<OrdemConcrets> getOrdens() {
        return ordens;
    }
}
