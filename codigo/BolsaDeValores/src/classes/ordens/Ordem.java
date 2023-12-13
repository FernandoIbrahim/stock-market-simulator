package classes.ordens;

import classes.Acao;

public interface Ordem {

    public OrderType getType();
    public Acao getAcao();
    
}
