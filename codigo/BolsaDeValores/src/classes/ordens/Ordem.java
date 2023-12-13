package classes.ordens;

import classes.Acao;
/**
 * Interface para a criação de ordens.
 * Design Pattern: Factory Method .
 * Define o comportamento das ordens.
 */
public interface Ordem {

    /**
     * 
     * @return Retorna o tipo da ordem
     */
    public OrderType getType();
    /**
     * 
     * @return Retorna a quantidade de ações da ordem
     */
    public Acao getAcao();
    
}
