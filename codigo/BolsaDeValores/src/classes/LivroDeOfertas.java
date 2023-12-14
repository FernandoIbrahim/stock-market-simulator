/**
 * Classe que representa o livro de ofertas de uma ação
 */
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

public class LivroDeOfertas {

    private List<OrdemConcrets> ordens;
    private List<Transacao> transacoes;
    private Acao acao;
    private List<AcaoObserver> acaoObservers;

    /**
     * Construtor da classe LivroDeOfertas
     * 
     * @param acao Recebe uma ação
     */
    public LivroDeOfertas(Acao acao) {

        this.ordens = new ArrayList<>();
        this.transacoes = new ArrayList<>();
        this.acaoObservers = new ArrayList<>();
        this.acao = acao;
    }

    /**
     * Adiciona uma ordem ao livro de ofertas
     * 
     * @param ordem Recebe uma ordem
     */
    public synchronized void addOrdem(OrdemConcrets ordem) {
        ordens.add(ordem);
        System.out.println(ordem.getNotificacao());
        notifyAllObservers(ordem);
        verficarOrdens();
    }

    /**
     * Pesquisa uma ordem no livro de ofertas
     * 
     * @param ordemInfo Recebe uma ordem
     * @return Retorna uma string com as ordens encontradas
     */
    public synchronized String pesquisarOperacaoInfo(OrdemInfo ordemInfo) {

        String result = "";

        List<OrdemConcrets> ordemsResuList = new ArrayList<>();
        int horarioOrdemInfo = ordemInfo.getDateTime().getHour();
        int diaOrdemInfo = ordemInfo.getDateTime().getDayOfMonth();
        int mesOrdemInfo = ordemInfo.getDateTime().getMonthValue();
        int anoOrdemInfo = ordemInfo.getDateTime().getYear();
        if (ordens != null) {
            return ordens.stream()
                    .filter(ordem -> ordem.getData().getHour() == horarioOrdemInfo &&
                            ordem.getData().getDayOfMonth() == diaOrdemInfo &&
                            ordem.getData().getMonthValue() == mesOrdemInfo &&
                            ordem.getData().getYear() == anoOrdemInfo)
                    .map(OrdemConcrets::getNotificacao)
                    .collect(Collectors.joining("\n"));
        }
        return null;
    }

    /**
     * Verifica se há ordens de compra e venda que podem ser executadas
     * 
     * @return Retorna a quantidade de transações realizadas
     */
    public synchronized int verficarOrdens() {
        int count = 0;
        for (OrdemConcrets ordemCompra : ordens) {
            for (OrdemConcrets ordemVenda : ordens) {
                if (ordemCompra != ordemVenda && ordemCompra.getType() == OrderType.COMPRA
                        && ordemVenda.getType() == OrderType.VENDA && ordemVenda.getAtivo() == true
                        && ordemCompra.getAtivo() == true) {
                    Transacao transacao = TransacaoManager.criarTransacao(ordemCompra, ordemVenda, this);
                    if (transacao != null) {
                        System.out.println(transacao.getNotificacao());
                        count++;
                    }
                }

            }
        }
        return count;
    }
    /**
     * Notifica todos os observers de uma ordem
     * @param ordem Recebe uma ordem
     */
    public void notifyAllObservers(OrdemConcrets ordem) {
        for (AcaoObserver acaoObserver : acaoObservers) {
            acaoObserver.update(ordem.getNotificacao());
            System.out.println("[NOTIFICACAO] O broker de ID: " + acaoObserver.getId()
                    + " foi notificado da ordem da Acao: " + ordem.getAcao().toString());
        }

    }
    /**
     * Adiciona um observer ao livro de ofertas
     * @param acaoObserver Recebe um observer
     */
    public void addObserver(AcaoObserver acaoObserver) {
        acaoObservers.add(acaoObserver);
    }
    /**
     * Remove um observer do livro de ofertas
     * @param acaoObserver Recebe um observer
     */
    public void removeObserver(AcaoObserver acaoObserver) {
        acaoObservers.remove(acaoObserver);
    }
    /**
     * Adiciona uma transação ao livro de ofertas
     * @param transacao Recebe uma transação
     */
    public void addTransacao(Transacao transacao) {
        this.transacoes.add(transacao);
    }
    /**
     * Define uma ação para o livro de ofertas
     * @param acao Recebe uma ação
     */
    public void setAcao(Acao acao) {
        this.acao = acao;
    }
    /**
     *  
     * @return Retorna uma ação
     */
    public Acao getAcao() {
        return acao;
    }
    /**
     * Verifica se um observer está no livro de ofertas
     * @param observer Recebe um observer
     * @return Retorna true se o observer estiver no livro de ofertas
     */
    public boolean temObserver(AcaoObserver observer) {
        return acaoObservers.contains(observer);
    }
    /**
     * 
     * @return Retorna uma lista de ordens
     */
    public List<OrdemConcrets> getOrdens() {
        return ordens;
    }
    /**
     * 
     * @return Retorna uma lista de transações
     */
    public List<Transacao> getTransacoes() {
        return transacoes;
    }
}
