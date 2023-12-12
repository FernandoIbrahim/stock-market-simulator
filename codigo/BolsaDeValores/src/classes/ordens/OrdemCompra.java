package classes.ordens;

import classes.Acao;
import classes.Broker;

public class OrdemCompra extends OrdemConcrets {
    
    OrderType tOrderType = OrderType.COMPRA;

    public OrdemCompra(Acao acao, int quantidade, double valor,Broker broker){
        super(acao, quantidade, valor,broker);
    }

    @Override
    public OrderType getType() {
        return tOrderType;
    }
   
    }


    
