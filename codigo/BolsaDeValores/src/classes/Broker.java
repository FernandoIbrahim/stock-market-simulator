/**
 * Classe Broker que representa um broker que observa a lista de ofertas de uma determinada ação
 */
package classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import classes.ordens.*;

public class Broker implements AcaoObserver{
    
    private static final Lock lock = new ReentrantLock();
    private BolsaDeValores bolsa;
    private List<Transacao> trasacoes;
    private List<String> notificacoes;
    private int id;
    private static int countId = 0;
    /**
     * Construtor da classe Broker
     * @param bolsa Recebe uma bolsa de valores
     */
    public Broker(BolsaDeValores bolsa){
        this.bolsa = bolsa;
        trasacoes = new ArrayList<>();
        notificacoes = new ArrayList<>();
        id = countId++;
    }
    /**
     *  Adiciona uma ordem ao livro de ofertas
     * @param ordem Recebe uma ordem
     */
    public synchronized void enviarOrdem(String ordem){  // a string deve seguir o seguinte fomato " compra; petr4; 100; 26,46; BKR "
        bolsa.addOperacaoCompraVenda(ordem, this);

    }
    /**
     * Adiciona uma ordem de venda ao livro de ofertas
     * @param ordem Recebe uma ordem
     */
    public synchronized void enviarOrdemInfo(String ordem){   // a string deve seguir o seguinte fomato " compra; petr4; 100; 26,46; BKR "
        notificacoes.add(bolsa.pesquisarOrdemInfo(ordem, this));
        if(bolsa.pesquisarOrdemInfo(ordem, this) != "")
            System.out.println("As ordens na hora solicitada foram: \n" + bolsa.pesquisarOrdemInfo(ordem, this)+ "\n\n");
        else{
            System.out.println("As ordens na hora solicitada foram: nulas");
        }
    }
    /**
     * 
     * @param sigla Recebe uma sigla
     */
    public void assinarAcao(String sigla){
        bolsa.assinarAcao(sigla, this);
    }

    /**
     *  Pesquisa uma ordem de informação no livro de ofertas
     * @param sigla Recebe uma sigla
     * @return Retorna uma string com as ordens encontradas
     */
    public String pesquisarOrdemInfo(String sigla){
        return bolsa.pesquisarOrdemInfo(sigla, this);
    }


    
    @Override
    public void update(String notificacao){
        notificacoes.add(notificacao);

    }

    
    @Override
    public String toString() {
        return " "+this.id+" ";
    }

    @Override
    public int getId() {
        return this.id;
    }

}
