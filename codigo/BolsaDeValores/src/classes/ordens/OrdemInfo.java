package classes.ordens;

import java.time.LocalDateTime;

import classes.Acao;

public class OrdemInfo implements Ordem  {
    
    private final OrderType tOrderType = OrderType.INFO;
    private Acao acao;
    private LocalDateTime dateTime;


    public OrdemInfo(Acao acao , LocalDateTime dateTime){
        this.acao = acao;
        this.dateTime = dateTime;
    }
    
    @Override
    public OrderType getType() {
        return tOrderType;
    }

}
