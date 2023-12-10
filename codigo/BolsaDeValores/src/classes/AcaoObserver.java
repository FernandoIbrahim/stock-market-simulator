package classes;

import classes.ordens.Ordem;

public interface AcaoObserver {

    public void update();
    public void addOrdem(Ordem ordem);
    
    
}
