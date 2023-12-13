/**
 * Classe que representa uma ordem de informação que implementa Ordem
 * Design Pattern: Factory Method.
 */

package classes.ordens;

import java.time.LocalDateTime;

import classes.Acao;
import classes.Broker;

public class OrdemInfo implements Ordem  {
    
    private final OrderType tOrderType = OrderType.INFO;
    private Acao acao;
    private LocalDateTime dateTime;
    private Broker broker;
    /**
     *  Construtor da classe OrdemInfo
     * @param acao  Recebe a ação que será criada a ordem
     * @param dateTime Recebe a data e hora da ordem
     * @param broker Recebe o broker que será feita a ordem
     */
    public OrdemInfo(Acao acao , LocalDateTime dateTime, Broker broker){
        this.acao = acao;
        this.dateTime = dateTime;
        this.broker = broker;
    }
   
    @Override
    public OrderType getType() {
        return tOrderType;
    }

    @Override
    public Acao getAcao() {
        return this.acao;
    }
        /**
         * 
         * @return Retorna o broker da ordem
         */

    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
