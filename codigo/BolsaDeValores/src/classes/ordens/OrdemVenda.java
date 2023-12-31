/**
 * Classe OrdemVenda que implementa a interface Ordem e extende a classe abstrata OrdemConcrets.
 * Essa classe é responsável por criar uma ordem de compra.
 */
package classes.ordens;

import java.time.format.DateTimeFormatter;

import classes.Acao;
import classes.Broker;

public class OrdemVenda extends OrdemConcrets {

    public static final String BLUE = "\u001B[34m";
    public static final String RESET = "\u001B[0m";

    private final OrderType tOrderType = OrderType.VENDA;
    /**
     * Construtor da classe OrdemVenda
     * @param acao Recebe a ação que será feita a ordem
     * @param quantidade Recebe a quantidade de ações que será feita a ordem
     * @param valor Recebe o valor da ordem
     * @param broker Recebe o broker que será feita a ordem
     */
    public OrdemVenda(Acao acao, int quantidade, double valor,Broker broker){
        super(acao, quantidade,valor, broker);
    }

    @Override
    public OrderType getType() {
        return tOrderType;
    }

    @Override
    public String getNotificacao() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format(BLUE+ "[ORDEM VENDA]"+ RESET+ " ->  acao: %s, quantidade: %d, broker: %s,  Acao: %b, valor: %.2f, data: %s}",
            getAcao(), getQuantidade(), getBroker(), getAcao(), getValor(), getData().format(formatter));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format(BLUE+ "[ORDEM VENDA]"+ RESET+ " ->  acao: %s, broker: %s, valor: %.2f, data: %s}",
            getAcao().toString(), getBroker().toString(),getValor(), getData().format(formatter));
    }
    
    
   
}
