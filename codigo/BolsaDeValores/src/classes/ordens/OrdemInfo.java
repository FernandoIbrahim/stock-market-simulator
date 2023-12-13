package classes.ordens;

import java.time.LocalDateTime;

import classes.Acao;
import classes.Broker;

public class OrdemInfo implements Ordem  {
    
    private final OrderType tOrderType = OrderType.INFO;
    private Acao acao;
    private LocalDateTime dateTime;
    private Broker broker;

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
    
    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
