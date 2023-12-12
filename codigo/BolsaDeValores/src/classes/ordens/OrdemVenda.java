package classes.ordens;

import classes.Acao;
import classes.Broker;

public class OrdemVenda extends OrdemConcrets {

    private final OrderType tOrderType = OrderType.VENDA;

    public OrdemVenda(Acao acao, int quantidade, Broker broker, boolean ativo){
        super(acao, quantidade, broker, ativo);
    }

    @Override
    public OrderType getType() {
        return tOrderType;
    }
    
   
}
