package classes.ordens;

import classes.Acao;
import classes.Broker;

public abstract class OrdemConcrets implements Ordem{
    private Acao acao;
    private int quantidade;
    private Broker broker;
    private boolean ativo;

     public OrdemConcrets(Acao acao, int quantidade, Broker broker, boolean ativo){
        this.acao = acao;
        this.quantidade = quantidade;
        this.broker = broker;
        this.ativo = ativo;
     }
     
     public boolean getAtivo() {
         return ativo;
     }

}
