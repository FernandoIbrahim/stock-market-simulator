package classes;

import classes.ordens.Ordem;

public interface AcaoObserver {

    public void update(String notificacao);
    public void addOrdem(Ordem ordem);
    
    
}
