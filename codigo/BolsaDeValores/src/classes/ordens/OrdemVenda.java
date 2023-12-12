package classes.ordens;

import classes.Acao;
import classes.Broker;

public class OrdemVenda extends OrdemConcrets {

    private final OrderType tOrderType = OrderType.VENDA;

    public OrdemVenda(Acao acao, int quantidade, double valor,Broker broker){
        super(acao, quantidade,valor, broker);
    }

    @Override
    public OrderType getType() {
        return tOrderType;
    }
    
   
}
