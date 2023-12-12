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
     public int getQuantidade() {
         return quantidade;
     }
     public Broker getBroker() {
         return broker;
     }
     public Acao getAcao() {
         return acao;
     }
     public void setAcao(Acao acao) {
         this.acao = acao;
     }
    
     public void alterarStatus() {
        this.ativo = !this.ativo ;
     }
     public void setBroker(Broker broker) {
         this.broker = broker;
     }
    
     public void atualizar(int quantidade) {
         this.quantidade = quantidade;
     }


}
