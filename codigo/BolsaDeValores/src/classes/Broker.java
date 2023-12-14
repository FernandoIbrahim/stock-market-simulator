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

    public Broker(BolsaDeValores bolsa){
        this.bolsa = bolsa;
        trasacoes = new ArrayList<>();
        notificacoes = new ArrayList<>();
        id = countId++;
    }

    public synchronized void enviarOrdem(String ordem){  // a string deve seguir o seguinte fomato " compra; petr4; 100; 26,46; BKR "
        bolsa.addOperacaoCompraVenda(ordem, this);

    }

    public synchronized void enviarOrdemInfo(String ordem){   // a string deve seguir o seguinte fomato " compra; petr4; 100; 26,46; BKR "
        notificacoes.add(bolsa.pesquisarOrdemInfo(ordem, this));
        if(bolsa.pesquisarOrdemInfo(ordem, this) != "")
            System.out.println("As ordens na hora solicitada foram: \n" + bolsa.pesquisarOrdemInfo(ordem, this)+ "\n\n");
        else{
            System.out.println("As ordens na hora solicitada foram: nulas");
        }
    }

    public void assinarAcao(String sigla){
        bolsa.assinarAcao(sigla, this);
    }


    public String pesquisarOrdemInfo(String sigla){
        return bolsa.pesquisarOrdemInfo(sigla, this);
    }


    //!Verificar forma de implementação dos determinados métods
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
