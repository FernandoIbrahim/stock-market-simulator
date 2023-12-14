
/**
 * Interface que define o comportamento de um observador
 */
package classes;


import classes.ordens.Ordem;

public interface AcaoObserver {
    /**
     * Atualiza o observador com uma notificação
     * @param notificacao Recebe uma notificação
     */
    public void update(String notificacao);
    /**
     * 
     * @return Retorna o id do observador
     */
    public int getId();
    
    
    
}
