/**
 * Interface para a criação de ordens.
 * Design Pattern: Factory Method
 */
package classes.ordens;

import classes.*;

public interface OperacaoFactory {

    /**
     * Cria uma ordem de compra.
     * @param acao Recebe a ação que será criada a ordem
     * @param quantidade Recebe a quantidade de ações que será feita a ordem
     * @param valor  Recebe o valor da ordem
     * @param broker Recebe o broker que será feita a ordem
     * @return Retorna a ordem criada
     */
    public OrdemConcrets criarOrdem( Acao acao, int quantidade, double valor,Broker broker);

}
