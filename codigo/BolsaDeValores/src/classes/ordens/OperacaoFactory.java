package classes.ordens;

import classes.*;

public interface OperacaoFactory {

    public OrdemConcrets criarOrdem( Acao acao, int quantidade, double valor,Broker broker);

}
