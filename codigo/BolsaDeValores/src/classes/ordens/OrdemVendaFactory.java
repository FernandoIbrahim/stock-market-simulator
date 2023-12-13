
/**
 * Classe Ordemvenda que implementa a interface OperacaoFactory
 * Essa classe é responsável por criar um objeto da classe Ordemvenda.
 * Design Pattern: Factory Method
 */
package classes.ordens;

import classes.Acao;
import classes.Broker;

public class OrdemVendaFactory implements OperacaoFactory {

     

    @Override
    public OrdemConcrets criarOrdem( Acao acao, int quantidade, double valor,Broker broker) {
        return new OrdemVenda(acao,quantidade,valor, broker);
    }

    
    
}
