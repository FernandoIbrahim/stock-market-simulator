package classes.ordens;

import classes.Acao;
import classes.Broker;

public interface OperacaoFactory {

    public Ordem criarOrdem(Acao acao, int quantidade, Broker broker, boolean ativo);
    
    
}
