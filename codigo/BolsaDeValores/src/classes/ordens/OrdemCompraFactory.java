package classes.ordens;

import classes.Acao;
import classes.Broker;

public class OrdemCompraFactory implements OperacaoFactory {

    
    public Ordem criarOrdem(Acao acao, int quantidade, Broker broker, boolean ativo){

        return new OrdemCompra(acao,quantidade,broker,ativo);
    }
        
    
    
}
