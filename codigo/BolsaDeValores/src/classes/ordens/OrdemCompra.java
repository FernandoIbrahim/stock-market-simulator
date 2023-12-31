package classes.ordens;
/**
 * Classe OrdemCompra que implementa a interface Ordem e extende a classe abstrata OrdemConcrets.
 * Essa classe é responsável por criar uma ordem de compra.
 */

import java.time.format.DateTimeFormatter;

import classes.Acao;
import classes.Broker;

public class OrdemCompra extends OrdemConcrets {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    OrderType tOrderType = OrderType.COMPRA;
    /**
     * Construtor da classe OrdemCompra
     * @param acao Recebe a ação que será feita a ordem
     * @param quantidade    Recebe a quantidade de ações que será feita a ordem
     * @param valor Recebe o valor da ordem
     * @param broker   Recebe o broker que será feita a ordem
     */
    public OrdemCompra(Acao acao, int quantidade, double valor, Broker broker){
        super(acao, quantidade, valor,broker);
    }
    /**
     *  Retorna o tipo da ordem
     */
    @Override
    public OrderType getType() {
        return tOrderType;
    }
    
    @Override
    public String getNotificacao() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format(RED + "[ORDEM COMPRA]"+ RESET+" ->  acao: %s, quantidade: %d, broker: %s, valor: %.2f, data: %s}",
            getAcao().toString(), getQuantidade(), getBroker().toString(), getValor(), getData().format(formatter));
    }
   
     @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format(RED + "[ORDEM COMPRA]"+ RESET +" ->  acao: %s, broker: %s, valor: %.2f, data: %s}",
            getAcao().toString(), getBroker().toString(),getValor(), getData().format(formatter));
    }
}


    
