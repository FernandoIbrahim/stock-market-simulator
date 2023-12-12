package classes.ordens;

import classes.Acao;
import classes.Broker;

public class OrdemVendaFactory implements OperacaoFactory {

     

    @Override
    public Ordem criarOrdem(Acao acao, int quantidade, Broker broker, boolean ativo) {

        return new OrdemVenda(acao, quantidade, broker, ativo);
    }

    
    
}
