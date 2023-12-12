package classes.ordens;

import classes.Acao;
import classes.Broker;

public class OrdemCompra extends OrdemConcrets {
    
    OrderType tOrderType = OrderType.COMPRA;

    public OrdemCompra(Acao acao, int quantidade, Broker broker, boolean ativo){
        super(acao, quantidade, broker, ativo);
    }

    @Override
    public OrderType getType() {
        return tOrderType;
    }
   
    }


    
