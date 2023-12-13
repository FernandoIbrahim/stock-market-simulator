package classes.ordens;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import classes.Acao;
import classes.Broker;

public abstract class OrdemConcrets implements Ordem, Comparable<OrdemConcrets>{
    private Acao acao;
    private int quantidade;
    private Broker broker;
    private boolean ativo;
    private double valor ;
    private LocalDateTime data;

     public OrdemConcrets(Acao acao, int quantidade, double valor,Broker broker){
        this.acao = acao;
        this.quantidade = quantidade;
        this.broker = broker;
        this.ativo = true;
        this.data = LocalDateTime.now();
        this.valor = valor;
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

     public void alterarStatus() {
        this.ativo = !this.ativo ;
     }
     public void setBroker(Broker broker) {
         this.broker = broker;
     }
    
     public void atualizar(int quantidade) {
         this.quantidade = quantidade;
     }

     public double getValor() {
         return valor;
     }

     public LocalDateTime getData() {
         return data;
     }


    public int compareTo(OrdemConcrets ordemConcrets){
        if(this.getQuantidade() > ordemConcrets.getQuantidade())
            return 1;
        else if(this.getQuantidade() == ordemConcrets.getQuantidade())
            return 0;

        return -1;
    }

    
    public abstract String getNotificacao();

}
