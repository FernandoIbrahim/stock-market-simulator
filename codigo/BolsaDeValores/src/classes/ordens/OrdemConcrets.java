package classes.ordens;
/**
 * Classe abstrata OrdemConcrets que implementa a interface Ordem.
 * 
 */
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

    /***
     * Construtor da classe OrdemConcrets
     * @param acao Recebe a ação que será feita a ordem
     * @param quantidade Recebe a quantidade de ações que será feita a ordem
     * @param valor Recebe o valor da ordem
     * @param broker Recebe o broker que será feita a ordem
     */
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
     /**
      * Altera o status da ordem
      */
     public void alterarStatus() {
        this.ativo = !this.ativo ;
     }
     public void setBroker(Broker broker) {
         this.broker = broker;
     }
     /**
      * Atualiza a quantidade de ações da ordem
      * @param quantidade Recebe a quantidade de ações que será feita a ordem
      */
     public void atualizar(int quantidade) {
         this.quantidade = quantidade;
     }

     public double getValor() {
         return valor;
     }

     public LocalDateTime getData() {
         return data;
     }

     /**
      * Compara a quantidade de ações de duas ordens
     * @param ordemConcrets Recebe a ordem que será comparada
      */
    public int compareTo(OrdemConcrets ordemConcrets){
        if(this.getQuantidade() > ordemConcrets.getQuantidade())
            return 1;
        else if(this.getQuantidade() == ordemConcrets.getQuantidade())
            return 0;

        return -1;
    }

    /**
     * 
     * @return Retorna a notificação da ordem (ToString)
     */
    public abstract String getNotificacao();

}
