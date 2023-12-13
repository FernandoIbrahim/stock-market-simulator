package classes.ordens;

import java.time.format.DateTimeFormatter;

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
    

    public String getNotificacao() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("Ordem Venda  ->  acao: %s, quantidade: %d, broker: %s,  Acao: %b, valor: %.2f,\n data: %s}",
            getAcao(), getQuantidade(), getBroker(), getAcao(), getValor(), getData().format(formatter));
    }
   
}


    
