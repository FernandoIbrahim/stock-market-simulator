package classes.ordens;
/**
 * Classe OrdemCompra que implementa a interface OperacaoFactory
 * Essa classe é responsável por criar um objeto da classe Ordem Compra.
 * Design Pattern: Factory Method
 */
import classes.Acao;
import classes.Broker;

public class OrdemCompraFactory implements OperacaoFactory {

    @Override
    public OrdemConcrets criarOrdem(Acao acao, int quantidade, double valor,Broker broker){

        return new OrdemCompra(acao,quantidade,valor,broker);
    }
        
    
    
}
