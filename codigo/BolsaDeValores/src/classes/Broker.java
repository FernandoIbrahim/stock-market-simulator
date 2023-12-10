package classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import classes.ordens.Ordem;

public class Broker implements AcaoObserver{

    private BolsaDeValores bolsa;
    private List<Transacao> trasacoes;
    private List<String> notificacoes;
    private int id;
    private static int countId = 0;

    public Broker(BolsaDeValores bolsa){
        trasacoes = new ArrayList<>();
        notificacoes = new ArrayList<>();
        id = countId++;
    }

    public void enviarOrdem(String ordem){  // a string deve seguir o seguinte fomato " compra; petr4; 100; 26,46; BKR "
        bolsa.addOperacao(ordem);
    }

    public void enviarOrdemInfo(String ordem){   // a string deve seguir o seguinte fomato " compra; petr4; 100; 26,46; BKR "
        bolsa.addOperacao(ordem);
    }

    public void assinar(String sigla){
        bolsa.assinar(sigla, this);
    }

    //! AlterarParaAddNotificação recebendo uma String 
    public void addOrdem(Ordem ordem){    

    }

    //!Verificar forma de implementação dos determinados métods
    public void update(){

    }


}
