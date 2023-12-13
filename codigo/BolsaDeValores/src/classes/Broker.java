package classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import classes.ordens.*;

public class Broker implements AcaoObserver{

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

    public void enviarOrdem(String ordem){  // a string deve seguir o seguinte fomato " compra; petr4; 100; 26,46; BKR "
        bolsa.addOperacaoCompraVenda(ordem, this);
    }

    // public void enviarOrdemInfo(String ordem){   // a string deve seguir o seguinte fomato " compra; petr4; 100; 26,46; BKR "
    //     bolsa.addOperacao(ordem, this);
    // }

    public void assinar(String sigla){
        bolsa.assinar(sigla, this);
    }


    public String pesquisarOrdemInfo(String sigla){
        return bolsa.pesquisarOrdemInfo(sigla, this);
    }


    //!Verificar forma de implementação dos determinados métods
    @Override
    public void update(String notificacao){
        notificacoes.add(notificacao);

    }

    
    


}
