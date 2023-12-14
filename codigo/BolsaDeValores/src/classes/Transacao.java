/**
 * Classe que define uma transação na bolsa de valores
 * 
 */
package classes;

import java.time.LocalDateTime;

import classes.ordens.Ordem;
import classes.ordens.OrdemConcrets;


public class Transacao {
    private OrdemConcrets ordemCompra ;
    private OrdemConcrets ordemVenda ;
    private LocalDateTime dataHora;
    private int quantidade;
    private double valor;
    private Acao acao;
    /**
     * Construtor da classe Transacao
     * @param ordemCompra Recebe uma Ordem de compra
     * @param ordemVenda Recebe uma Ordem de venda
     * @param datahora  Recebe a data e hora da transação
     * @param quantidade  Recebe a quantidade de ações transacionadas
     * @param valor Recebe o valor da transação
     * @param acao Recebe a ação transacionada
     */
    public Transacao(OrdemConcrets ordemCompra, OrdemConcrets ordemVenda, LocalDateTime datahora, int quantidade, double valor , Acao acao){

        this.ordemCompra = ordemCompra;
        this.ordemVenda = ordemVenda;
        this.dataHora = datahora;
        this.quantidade = quantidade;
        this.valor = valor;
        this.acao = acao;
    }
    /**
     * 
     * @return Retorna a ordem de compra(toString)
     */
    public String getNotificacao(){
        return "\n\n---------------------------------------------------------------\n[Transacao Criada]: \n" + ordemCompra.toString() + "\n---\n" + ordemVenda.toString() + "\n---\n Quantidade: " + quantidade + "\nAcao: " + acao.toString() + "\nValor: " + valor + "\n---------------------------------------------------------------\n\n"; 
    }


}
