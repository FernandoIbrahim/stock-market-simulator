package classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Broker {

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

    public void enviarOrdem(String sigla, int quantidade, String tipo){
        bolsa.addOperacao(sigla, quantidade, tipo);
    }

    public void enviarOrdemInfo(String sigla, LocalDateTime dataHora){
        bolsa.addOperacao(sigla, dataHora);
    }

    public void assinar(String sigla){
        bolsa.assinar(sigla, this);
    }

    private void upDate(){

    }

}
