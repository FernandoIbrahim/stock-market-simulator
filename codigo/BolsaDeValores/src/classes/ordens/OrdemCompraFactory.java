package classes.ordens;

import classes.Acao;
import classes.Broker;

public class OrdemCompraFactory implements OperacaoFactory {

    
    public Ordem criarOrdem(Acao acao, int quantidade, double valor,Broker broker){

        return new OrdemCompra(acao,quantidade,valor,broker);
    }
        
    
    
}
