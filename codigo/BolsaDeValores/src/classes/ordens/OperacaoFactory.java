package classes.ordens;

import classes.Acao;
import classes.Broker;

public interface OperacaoFactory {

    public Ordem criarOrdem(Acao acao, int quantidade, double valor,Broker broker);
    
    
}
